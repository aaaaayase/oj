package com.yun.judge.service;

import com.yun.judge.domain.SandBoxExecuteResult;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/19 21:17
 * @desciption:
 */
public interface ISandBoxService {
    SandBoxExecuteResult exeJavaCode(Long userId,String userCode, List<String> inputList);
}
