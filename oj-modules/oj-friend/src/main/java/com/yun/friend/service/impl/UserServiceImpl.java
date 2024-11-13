package com.yun.friend.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.yun.common.core.enums.ResultCode;
import com.yun.common.security.exception.ServiceException;
import com.yun.friend.domain.dto.UserDTO;
import com.yun.friend.mapper.IUserMapper;
import com.yun.friend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yun
 * @date 2024/11/13 19:39
 * @desciption: 用户相关业务逻辑
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public int sendCode(UserDTO userDTO) {
        if (checkPhone(userDTO.getPhone())) {
            throw new ServiceException(ResultCode.FAILED_USER_PHONE);
        }
        String code = RandomUtil.randomNumbers(6);
        return 0;
    }

    public static boolean checkPhone(String phone) {
        Pattern regex = Pattern.compile("^1[2|3|4|5|6|7|8|9][0-9]\\d{8}$");
        Matcher m = regex.matcher(phone);
        return m.matches();
    }
}
