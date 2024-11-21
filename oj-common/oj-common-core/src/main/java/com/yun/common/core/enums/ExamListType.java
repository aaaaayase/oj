package com.yun.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yun
 * @date 2024/11/16 9:47
 * @desciption:
 */
@Getter
@AllArgsConstructor
public enum ExamListType {

    EXAM_UN_FINISH_LIST(0),
    EXAM_HISTORY_lIST(1),
    USER_EXAM_lIST(2);
    private final Integer value;

}
