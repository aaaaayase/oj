package com.yun.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yun.common.core.constants.HttpConstants;
import com.yun.common.core.domain.LoginUser;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.vo.LoginUserVO;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.core.enums.UserIdentity;
import com.yun.common.security.exception.ServiceException;
import com.yun.common.security.service.TokenService;
import com.yun.system.domain.SysUser;
import com.yun.system.domain.dto.SysUserSaveDTO;
import com.yun.system.mapper.ISysUserMapper;
import com.yun.system.service.ISysUserService;
import com.yun.system.utils.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/10/16 9:31
 * @desciption: 管理员相关业务逻辑
 */
@Service
@RefreshScope
public class SysUserService implements ISysUserService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserMapper sysUserMapper;

    @Override
    public R<String> login(String userAccount, String password) {
        // 1.通过账号去查询数据库中的信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper.select(SysUser::getUserId, SysUser::getPassword, SysUser::getNickName).eq(SysUser::getUserAccount, userAccount));
        R loginResult = new R();
        if (sysUser == null) {
//            loginResult.setMsg(ResultCode.FAILED_USER_NOT_EXISTS.getMsg());
//            loginResult.setCode(ResultCode.FAILED_USER_NOT_EXISTS.getCode());
//            return loginResult;
            return R.fail(ResultCode.FAILED_USER_NOT_EXISTS);
        }

        if (BCryptUtils.matchesPassword(password, sysUser.getPassword())) {
//            loginResult.setCode(ResultCode.SUCCESS.getCode());
//            loginResult.setMsg(ResultCode.SUCCESS.getMsg());

            return R.ok(tokenService.createToken(sysUser.getUserId(), secret, UserIdentity.ADMIN.getValue(), sysUser.getNickName()));
        }

//        loginResult.setMsg(ResultCode.FAILED_LOGIN.getMsg());
//        loginResult.setCode(ResultCode.FAILED_LOGIN.getCode());
//
//        return loginResult;
        return R.fail(ResultCode.FAILED_LOGIN);
    }

    // 增加管理员用户
    @Override
    public int add(SysUserSaveDTO saveDTO) {
        // 校验新增的对象
        List<SysUser> sysUserList = sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserAccount, saveDTO.getUserAccount()));
        if (CollectionUtil.isNotEmpty(sysUserList)) {
            throw new ServiceException(ResultCode.AILED_USER_EXISTS);
        }
        // 将DTO转为实体
        SysUser sysUser = new SysUser();
        sysUser.setUserAccount(saveDTO.getUserAccount());
        sysUser.setPassword(BCryptUtils.encryptPassword(saveDTO.getPassword()));
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public R<LoginUserVO> info(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX, StrUtil.EMPTY);
        }
        LoginUser loginUser = tokenService.getLoginUser(token, secret);
        if (loginUser == null) {
            return R.fail();
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setNickName(loginUser.getNickName());

        return R.ok(loginUserVO);
    }
}
