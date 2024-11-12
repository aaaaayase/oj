package com.yun.system.mapper.exam;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.system.domain.exam.Exam;
import com.yun.system.domain.exam.dto.ExamQueryDTO;
import com.yun.system.domain.exam.vo.ExamVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/10 19:31
 * @desciption: 竞赛相关-访问数据库接口
 */
public interface IExamMapper extends BaseMapper<Exam> {
    List<ExamVO> selectExamList(ExamQueryDTO examQueryDTO);
}
