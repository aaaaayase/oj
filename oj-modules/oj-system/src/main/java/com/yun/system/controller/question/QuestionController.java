package com.yun.system.controller.question;

import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.system.domain.question.dto.QuestionQueryDTO;
import com.yun.system.domain.question.vo.QuestionVO;
import com.yun.system.service.question.IQuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/8 20:59
 * @desciption: 题目相关接口
 */
@RestController
@RequestMapping("/question")
@Tag(name = "题目管理接口")
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/list")
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO) {
        return getTableDataInfo(questionService.list(questionQueryDTO));
    }
}
