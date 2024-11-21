package com.yun.friend.controller.user;

import com.yun.api.domain.vo.UserQuestionResultVO;
import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.friend.domain.user.dto.UserSubmitDTO;
import com.yun.friend.service.user.IUserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yun
 * @date 2024/11/19 14:39
 * @desciption: 用户答题相关接口
 */
@RestController
@RequestMapping("/user/question")
public class UserQuestionController extends BaseController {

    @Autowired
    private IUserQuestionService userQuestionService;

    // 用户代码提交
    @PostMapping("/submit")
    public R<UserQuestionResultVO> submit(@RequestBody UserSubmitDTO submitDTO) {
        return userQuestionService.submit(submitDTO);
    }

    @PostMapping("/rabbit/submit")
    public R<Void> rabbitSubmit(@RequestBody UserSubmitDTO submitDTO) {
        return toR(userQuestionService.rabbitSubmit(submitDTO));
    }

    @GetMapping("/exe/result")
    public R<UserQuestionResultVO> exeResult(Long examId, Long questionId, String currentTime) {
        return R.ok(userQuestionService.exeResult(examId, questionId, currentTime));
    }

}
