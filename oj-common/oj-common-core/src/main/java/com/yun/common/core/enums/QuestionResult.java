package com.yun.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/19 15:17
 * @desciption: 提交的代码是否通过
 */
@Getter
@AllArgsConstructor
public enum QuestionResult {

    ERROR(0),
    PASS(1);

    private Integer value;

}
