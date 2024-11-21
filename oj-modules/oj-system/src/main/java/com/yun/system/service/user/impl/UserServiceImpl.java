package com.yun.system.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.security.exception.ServiceException;
import com.yun.system.domain.user.User;
import com.yun.system.domain.user.dto.UserDTO;
import com.yun.system.domain.user.dto.UserQueryDTO;
import com.yun.system.domain.user.vo.UserVO;
import com.yun.system.manager.UserCacheManager;
import com.yun.system.mapper.user.IUserMapper;
import com.yun.system.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/13 11:27
 * @desciption: 客户端用户相关业务逻辑
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private UserCacheManager userCacheManager;

    @Override
    public List<UserVO> list(UserQueryDTO userQueryDTO) {
        PageHelper.startPage(userQueryDTO.getPageNum(), userQueryDTO.getPageSize());
        // 当你查询出满足条件的结果时 pagehelper就会自动帮你给分
        return userMapper.selectUserList(userQueryDTO);
    }

    @Override
    public int updateStatus(UserDTO userDTO) {
        User user = userMapper.selectById(userDTO.getUserId());
        if (user == null) {
            throw new ServiceException(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        user.setStatus(userDTO.getStatus());
        userCacheManager.updateStatus(user.getUserId(),user.getStatus());
        return userMapper.updateById(user);
    }
}
