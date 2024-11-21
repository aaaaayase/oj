package com.yun.judge.domain;

import lombok.Data;

/**
 * @author yun
 * @date 2024/11/20 8:17
 * @desciption: 编译结果
 */
@Data
public class CompileResult {

    private boolean compiled;  //编译是否成功

    private String exeMessage;  //编译输出信息 （错误信息）
}
