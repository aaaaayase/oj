package com.yun.system.controller.question;

import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.system.domain.question.dto.QuestionAddDTO;
import com.yun.system.domain.question.dto.QuestionEditDTO;
import com.yun.system.domain.question.dto.QuestionQueryDTO;
import com.yun.system.domain.question.vo.QuestionDetailVO;
import com.yun.system.service.question.IQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/8 20:59
 * @desciption: 题目相关接口
 */
@RestController
@RequestMapping("/question")
@Tag(name = "管理端题目接口")
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/list")
    @Operation(summary = "获取题目列表", description = "传递参数获取满足条件的题目列表")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO) {
        return getTableDataInfo(questionService.list(questionQueryDTO));
    }


    @PostMapping("/add")
    @Operation(summary = "添加题目", description = "传递题目信息添加题目")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3004", description = "题目已经存在")
    @ApiResponse(responseCode = "3002", description = "参数校验失败")
    public R<Void> add(@RequestBody @Validated QuestionAddDTO questionAddDTO) {
        return toR(questionService.add(questionAddDTO));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取题目信息", description = "根据题目id来获取题目详细信息返回给前端")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "3003", description = "题目不存在")
    public R<QuestionDetailVO> detail(Long questionId) {
        return R.ok(questionService.detail(questionId));
    }

    @PutMapping("/edit")
    @Operation(summary = "编辑题目信息", description = "根据前端传过来的信息来修改数据库中的题目信息")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "3003", description = "题目不存在")
    public R<Void> edit(@RequestBody QuestionEditDTO questionEditDTO) {
        return toR(questionService.edit(questionEditDTO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目", description = "根据前端传过来的题目id来删除数据库中的题目")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "3000", description = "操作失败")
    @ApiResponse(responseCode = "3003", description = "题目不存在")
    public R<Void> delete(Long questionId) {
        return toR(questionService.delete(questionId));
    }
}
