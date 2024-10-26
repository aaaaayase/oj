package com.yun.common.security.service;

import cn.hutool.core.lang.UUID;
import com.yun.common.core.constants.CacheConstants;
import com.yun.common.core.constants.JwtConstants;
import com.yun.common.redis.service.RedisService;
import com.yun.common.security.domain.LoginUser;
import com.yun.common.security.utils.JwtUtils;
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
public class TokenService {

    @Autowired
    private RedisService redisService;

    public String createToken(Long userId, String secret, Integer identity) {
        // 生成token
        Map<String, Object> claims = new HashMap<>();
        String userKey = UUID.fastUUID().toString();
        claims.put(JwtConstants.LOGIN_USER_ID, userId);
        claims.put(JwtConstants.LOGIN_USER_KEY, userKey);
        String token = JwtUtils.createToken(claims, secret);
        // 在redis中存储敏感信息 使用identity来表明用户身份 1 普通用户 2 管理员用户
        // 使用String键值对来进行存储 统一前缀为logintoken:userId userId是通过雪花算法生成的
        // 这里使用hutool插件生成全局唯一的userUUID存入redis

        String key = CacheConstants.LOGIN_TOKEN_KEY + userKey;
        LoginUser loginUser = new LoginUser();
        loginUser.setIdentity(identity);
        redisService.setCacheObject(key, loginUser, CacheConstants.EXP, TimeUnit.MINUTES);

        return token;
    }


}
