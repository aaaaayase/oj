package com.yun.judge.service;

import com.yun.api.domain.dto.JudgeSubmitDTO;
import com.yun.api.domain.vo.UserQuestionResultVO;

/**
 * @author yun
 * @date 2024/11/19 20:34
 * @desciption: 判题相关接口
 */
public interface IJudgeService {
    UserQuestionResultVO doJudgeJavaCode(JudgeSubmitDTO judgeSubmitDTO);
}
