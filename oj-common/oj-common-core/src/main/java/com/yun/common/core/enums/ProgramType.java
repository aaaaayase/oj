package com.yun.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yun
 * @date 2024/11/19 14:51
 * @desciption: 代码类型
 */
@Getter
@AllArgsConstructor
public enum ProgramType {

    JAVA(0, "Java语言"),
    CPP(1, "C++语言"),
    GO(2, "Go语言");

    private Integer value;

    private String desc;
}
