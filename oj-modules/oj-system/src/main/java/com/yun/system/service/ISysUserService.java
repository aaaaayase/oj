package com.yun.system.service;

import com.yun.common.core.domain.R;

/**
 * @author yun
 * @date 2024/10/16 9:17
 * @desciption: 管理员相关接口
 */
public interface ISysUserService {

    /**
     * 管理员登录接口
     *
     * @param userAccount
     * @param password
     * @return
     */
    R<Void> login(String userAccount, String password);
}
