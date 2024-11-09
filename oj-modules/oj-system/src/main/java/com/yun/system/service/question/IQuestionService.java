package com.yun.system.service.question;

import com.yun.common.core.domain.TableDataInfo;
import com.yun.system.domain.question.dto.QuestionQueryDTO;
import com.yun.system.domain.question.vo.QuestionVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/8 22:10
 * @desciption: 问题相关接口
 */
public interface IQuestionService {
    List<QuestionVO> list(QuestionQueryDTO questionQueryDTO);
}
