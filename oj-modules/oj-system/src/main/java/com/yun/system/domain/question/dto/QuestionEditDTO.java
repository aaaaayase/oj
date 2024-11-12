package com.yun.system.domain.question.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/9 21:29
 * @desciption: 编辑问题传入后端参数类
 */
@Getter
@Setter
public class QuestionEditDTO extends QuestionAddDTO {
    private Long questionId;
}
