package com.yun.job.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.job.domain.user.UserScore;

import java.util.List;
import java.util.Set;

/**
 * @author yun
 * @date 2024/11/22 9:06
 * @desciption: 用户提交结果接口
 */
public interface IUserSubmitMapper extends BaseMapper<UserScore> {

    List<UserScore> selectUserScoreList(Set<Long> examIdSet);
}
