package com.yun.friend.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.friend.domain.exam.vo.ExamRankVO;
import com.yun.friend.domain.exam.vo.ExamVO;
import com.yun.friend.domain.user.UserExam;
import feign.template.Literal;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/17 10:00
 * @desciption:
 */
public interface IUserExamMapper extends BaseMapper<UserExam> {

    List<ExamVO> selectUserExamList(Long userId);

    List<ExamRankVO> selectExamRankList(Long examId);
}