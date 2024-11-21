package com.yun.judge.controller;

import com.yun.api.domain.dto.JudgeSubmitDTO;
import com.yun.api.domain.vo.UserQuestionResultVO;
import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.judge.service.IJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yun
 * @date 2024/11/19 20:31
 * @desciption: 判题相关接口
 */
@RestController
@RequestMapping("/judge")
public class JudgeController extends BaseController {
    @Autowired
    private IJudgeService judgeService;

    @PostMapping("/doJudgeJavaCode")
    public R<UserQuestionResultVO> doJudgeJavaCode(@RequestBody JudgeSubmitDTO judgeSubmitDTO) {
        return R.ok(judgeService.doJudgeJavaCode(judgeSubmitDTO));
    }
}
