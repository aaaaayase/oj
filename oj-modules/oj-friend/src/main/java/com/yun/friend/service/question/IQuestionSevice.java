package com.yun.friend.service.question;

import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.domain.question.dto.QuestionQueryDTO;
import com.yun.friend.domain.question.vo.QuestionDetailVO;

/**
 * @author yun
 * @date 2024/11/18 9:18
 * @desciption: 问题相关接口
 */
public interface IQuestionSevice {
    TableDataInfo list(QuestionQueryDTO questionQueryDTO);

    QuestionDetailVO detail(Long questionId);

    String preQuestion(Long questionId);

    String nextQuestion(Long questionId);
}
