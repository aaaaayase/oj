package com.yun.friend.mapper.exam;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.friend.domain.exam.Exam;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.domain.exam.vo.ExamVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/15 15:43
 * @desciption: 竞赛相关接口-访问数据库
 */
public interface IExamMapper extends BaseMapper<Exam> {
    List<ExamVO> selectExamList(ExamQueryDTO examQueryDTO);
}
