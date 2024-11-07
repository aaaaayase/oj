package com.yun.system.service;

import com.yun.common.core.domain.R;
import com.yun.common.core.domain.vo.LoginUserVO;
import com.yun.system.domain.dto.SysUserSaveDTO;

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
    R<String> login(String userAccount, String password);

    /**
     * 增加管理员
     *
     * @param saveDTO
     * @return
     */
    int add(SysUserSaveDTO saveDTO);

    R<LoginUserVO> info(String token);
}
