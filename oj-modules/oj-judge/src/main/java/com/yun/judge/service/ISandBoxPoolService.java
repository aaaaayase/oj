package com.yun.judge.service;

import com.yun.judge.domain.SandBoxExecuteResult;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/20 10:15
 * @desciption: 容器池服务相关接口
 */
public interface ISandBoxPoolService {
    SandBoxExecuteResult exeJavaCode(Long userId, String userCode, List<String> inputList);
}
