package com.yun.api.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/19 20:18
 * @desciption: 代码执行结果
 */
@Getter
@Setter
public class UserExeResult {

    private String input;

    private String output;   //期望输出

    private String exeOutput; //实际输出
}