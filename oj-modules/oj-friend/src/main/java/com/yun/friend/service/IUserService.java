package com.yun.friend.service;

import com.yun.friend.domain.dto.UserDTO;

/**
 * @author yun
 * @date 2024/11/13 19:38
 * @desciption: 用户相关接口
 */
public interface IUserService {
    int sendCode(UserDTO userDTO);
}
