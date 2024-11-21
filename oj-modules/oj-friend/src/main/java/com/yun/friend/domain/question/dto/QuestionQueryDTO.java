package com.yun.friend.domain.question.dto;

import com.yun.common.core.domain.dto.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/18 9:15
 * @desciption: 问题列表查询传递给后端的参数类
 */
@Setter
@Getter
public class QuestionQueryDTO extends PageQueryDTO {

    private String keyword;

    private Integer difficulty;

}
