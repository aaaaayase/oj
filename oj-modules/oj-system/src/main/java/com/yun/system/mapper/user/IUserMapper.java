package com.yun.system.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.system.domain.user.User;
import com.yun.system.domain.user.dto.UserQueryDTO;
import com.yun.system.domain.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/13 11:29
 * @desciption: 客户端用户相关-访问数据库接口
 */
public interface IUserMapper extends BaseMapper<User> {
    List<UserVO> selectUserList(UserQueryDTO userQueryDTO);
}
