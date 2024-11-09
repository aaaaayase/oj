package com.yun.system.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.system.domain.question.Question;
import com.yun.system.domain.question.dto.QuestionQueryDTO;
import com.yun.system.domain.question.vo.QuestionVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/8 22:17
 * @desciption: 问题相关-访问数据库接口
 */
public interface IQuestionMapper extends BaseMapper<Question> {
    List<QuestionVO> selectQuestionList(QuestionQueryDTO questionQueryDTO);
}
