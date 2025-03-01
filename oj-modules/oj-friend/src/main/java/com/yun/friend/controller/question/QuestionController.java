package com.yun.friend.controller.question;

import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.domain.question.dto.QuestionQueryDTO;
import com.yun.friend.domain.question.vo.QuestionDetailVO;
import com.yun.friend.domain.question.vo.QuestionVO;
import com.yun.friend.service.question.IQuestionSevice;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/18 9:16
 * @desciption: 客户端问题相关接口
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionSevice questionSevice;

    @GetMapping("/semiLogin/list")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO) {
        return questionSevice.list(questionQueryDTO);
    }

    @GetMapping("/semiLogin/hotList")
    public R<List<QuestionVO>> hotList() {
        return R.ok(questionSevice.hotList());
    }

    @GetMapping("/detail")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public R<QuestionDetailVO> detail(Long questionId) {
        return R.ok(questionSevice.detail(questionId));
    }

    @GetMapping("/preQuestion")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public R<String> preQuestion(Long questionId) {
        return R.ok(questionSevice.preQuestion(questionId));
    }

    @GetMapping("/nextQuestion")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public R<String> nextQuestion(Long questionId) {
        return R.ok(questionSevice.nextQuestion(questionId));
    }

}
