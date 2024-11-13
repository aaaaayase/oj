package com.yun.system.service.user;

import com.yun.system.domain.user.dto.UserDTO;
import com.yun.system.domain.user.dto.UserQueryDTO;
import com.yun.system.domain.user.vo.UserVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/13 11:26
 * @desciption: 客户端用户相关接口
 */
public interface IUserService {
    List<UserVO> list(UserQueryDTO userQueryDTO);

    int updateStatus(UserDTO userDTO);

}
