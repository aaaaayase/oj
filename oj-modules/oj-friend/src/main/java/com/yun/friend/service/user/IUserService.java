package com.yun.friend.service.user;

import com.yun.common.core.domain.R;
import com.yun.common.core.domain.vo.LoginUserVO;
import com.yun.friend.domain.user.dto.UserDTO;

/**
 * @author yun
 * @date 2024/11/13 19:38
 * @desciption: 用户相关接口
 */
public interface IUserService {
    boolean sendCode(UserDTO userDTO);

    String codeLogin(String phone, String code);

    boolean logout(String token);

    R<LoginUserVO> info(String token);
}