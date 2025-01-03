package com.yun.common.security.service;

import cn.hutool.core.lang.UUID;
import com.yun.common.core.constants.CacheConstants;
import com.yun.common.core.constants.JwtConstants;
import com.yun.common.redis.service.RedisService;
import com.yun.common.core.domain.LoginUser;
import com.yun.common.core.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yun
 * @date 2024/10/26 10:49
 * @desciption: 用户操作用户生成token
 */
@Service
@Slf4j
public class TokenService {

    @Autowired
    private RedisService redisService;

    // 创建token并且将登录对象的信息存储到redis中
    public String createToken(Long userId, String secret, Integer identity, String nickName, String headImage) {
        // 生成token
        Map<String, Object> claims = new HashMap<>();
        String userKey = UUID.fastUUID().toString();
        claims.put(JwtConstants.LOGIN_USER_ID, userId);
        claims.put(JwtConstants.LOGIN_USER_KEY, userKey);
        String token = JwtUtils.createToken(claims, secret);
        // 在redis中存储敏感信息 使用identity来表明用户身份 1 普通用户 2 管理员用户
        // 使用String键值对来进行存储 统一前缀为logintoken:userId userId是通过雪花算法生成的
        // 这里使用hutool插件生成全局唯一的userUUID存入redis

        String key = getTokenKey(userKey);
        LoginUser loginUser = new LoginUser();
        loginUser.setIdentity(identity);
        loginUser.setNickName(nickName);
        loginUser.setHeadImage(headImage);
        redisService.setCacheObject(key, loginUser, CacheConstants.EXP, TimeUnit.MINUTES);

        return token;
    }

    // 延长token的有效时间 实际上就是延长redis中存储的用于身份认证的敏感信息的有效时间 --操作redis
    // 调用时机为身份认证之后 controller之前
    public void extendToken(Claims claims) {
//        Claims claims;
//        try {
//            claims = JwtUtils.parseToken(token, secret);
//            if (claims == null) {
//                log.error("解析token：{}出现异常", token);
//                return;
//            }
//        } catch (Exception e) {
//            log.error("解析token：{}出现异常", token, e);
//            return;
//        }
//        String userKey = JwtUtils.getUserKey(claims);
        String userKey = getUserKey(claims);
        if (userKey == null) {
            return;
        }
        String tokenKey = getTokenKey(userKey);

        // 获取redis中的键值对剩余的ttl
        Long expire = redisService.getExpire(tokenKey, TimeUnit.MINUTES);
        // 发现剩余ttl时间已经不多 延长或者说刷新至720mins
        if (expire != null && expire < CacheConstants.REFRESH_TIME) {
            redisService.expire(tokenKey, CacheConstants.EXP, TimeUnit.MINUTES);
        }
    }

    // 获取redis中对象
    public LoginUser getLoginUser(String token, String secret) {
        String userKey = getUserKey(token, secret);
        if (userKey == null) {
            return null;
        }
        return redisService.getCacheObject(getTokenKey(userKey), LoginUser.class);
    }

    // 删除redis中对象
    public boolean deleteLoginUser(String token, String secret) {
        String userKey = getUserKey(token, secret);
        if (userKey == null) {
            return false;
        }
        return redisService.deleteObject(getTokenKey(userKey));
    }

    public Long getUserId(Claims claims) {
        if (claims == null) return null;
        return Long.valueOf(JwtUtils.getUserId(claims));
    }

    public String getUserKey(Claims claims) {
        if (claims == null) return null;
        return JwtUtils.getUserKey(claims);
    }

    private String getUserKey(String token, String secret) {
        Claims claims = getClaims(token, secret);
        if (claims == null) return null;
        return JwtUtils.getUserKey(claims);
    }

    public Claims getClaims(String token, String secret) {
        Claims claims;
        try {
            claims = JwtUtils.parseToken(token, secret);
            if (claims == null) {
                log.error("解析token：{}出现异常", token);
                return null;
            }
        } catch (Exception e) {
            log.error("解析token：{}出现异常", token, e);
            return null;
        }
        return claims;
    }

    private String getTokenKey(String userKey) {
        return CacheConstants.LOGIN_TOKEN_KEY + userKey;
    }


    public void refreshLoginUser(String nickName, String headImage, String userKey) {
        String key = getTokenKey(userKey);
        LoginUser loginUser = redisService.getCacheObject(key, LoginUser.class);
        loginUser.setNickName(nickName);
        loginUser.setHeadImage(headImage);
        redisService.setCacheObject(key, loginUser);
    }
}
