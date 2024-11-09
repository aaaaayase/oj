package com.yun.system.domain.question.dto;

import com.yun.common.core.domain.dto.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/8 21:00
 * @desciption: 问题传输后端数据
 */
@Getter
@Setter
public class QuestionQueryDTO extends PageQueryDTO {
    private Integer difficulty;

    private String title;

}
