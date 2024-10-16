package com.yun.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yun.common.core.domain.R;
import com.yun.common.core.enums.ResultCode;
import com.yun.system.domain.SysUser;
import com.yun.system.mapper.ISysUserMapper;
import com.yun.system.service.ISysUserService;
import com.yun.system.utils.BCryptUtils;
import org.springframework.stereotype.Service;

/**
 * @author yun
 * @date 2024/10/16 9:31
 * @desciption: 管理员相关业务逻辑
 */
@Service
public class SysUserService implements ISysUserService {

    private ISysUserMapper sysUserMapper;

    @Override
    public R<Void> login(String userAccount, String password) {
        // 1.通过账号去查询数据库中的信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper.select(SysUser::getPassword).eq(SysUser::getUserAccount, userAccount));
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

            return R.ok();
        }

//        loginResult.setMsg(ResultCode.FAILED_LOGIN.getMsg());
//        loginResult.setCode(ResultCode.FAILED_LOGIN.getCode());
//
//        return loginResult;
        return R.fail(ResultCode.FAILED_LOGIN);
    }
}
