package com.yun.api;

import com.yun.api.domain.dto.JudgeSubmitDTO;
import com.yun.api.domain.vo.UserQuestionResultVO;
import com.yun.common.core.constants.Constants;
import com.yun.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yun
 * @date 2024/11/19 20:13
 * @desciption: 远程判题服务接口
 */
@FeignClient(contextId = "RemoteJudgeService", value = Constants.JUDGE_SERVICE)
public interface RemoteJudgeService {

    @PostMapping("/judge/doJudgeJavaCode")
    R<UserQuestionResultVO> doJudgeJavaCode(@RequestBody JudgeSubmitDTO judgeSubmitDTO);
}