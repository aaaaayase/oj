package com.yun.friend.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.friend.domain.user.UserSubmit;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/19 21:39
 * @desciption:
 */
public interface IUserSubmitMapper extends BaseMapper<UserSubmit> {
    UserSubmit selectCurrentUserSubmit(Long userId, Long questionId, Long examId, String currentTime);

    List<Long> selectHostQuestionList();
}
