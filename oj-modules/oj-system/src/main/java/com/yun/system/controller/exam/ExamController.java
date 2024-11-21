package com.yun.system.controller.exam;

import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.system.domain.exam.dto.ExamAddDTO;
import com.yun.system.domain.exam.dto.ExamEditDTO;
import com.yun.system.domain.exam.dto.ExamQueryDTO;
import com.yun.system.domain.exam.dto.ExamQuestionAddDTO;
import com.yun.system.domain.exam.vo.ExamDetailVO;
import com.yun.system.service.exam.IExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yun
 * @date 2024/11/10 19:30
 * @desciption: 竞赛相关接口
 */
@RestController
@RequestMapping("/exam")
@Tag(name = "管理端竞赛接口")
public class ExamController extends BaseController {

    @Autowired
    private IExamService examService;

    @GetMapping("/list")
    @Operation(summary = "获取竞赛列表", description = "传递参数获取满足条件的竞赛列表")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        return getTableDataInfo(examService.list(examQueryDTO));
    }

    @PostMapping("/add")
    @Operation(summary = "添加竞赛", description = "传递参数添加竞赛")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3004", description = "竞赛已经存在")
    @ApiResponse(responseCode = "3201", description = "竞赛开始时间不能早于当前时间")
    @ApiResponse(responseCode = "3202", description = "竞赛开始时间不能晚于结束时间")
    public R<String> add(@RequestBody ExamAddDTO examAddDTO) {
        return R.ok(examService.add(examAddDTO));
    }

    @PostMapping("/question/add")
    @Operation(summary = "添加题目", description = "向相关竞赛中添加题目")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3203", description = "竞赛不存在")
    @ApiResponse(responseCode = "3204", description = "为竞赛新增的题目不存在")
    @ApiResponse(responseCode = "3205", description = "竞赛已经开始")
    @ApiResponse(responseCode = "3208", description = "竞赛已经发布不能进行编辑删除等操作")
    public R<Void> questionAdd(@RequestBody ExamQuestionAddDTO examQuestionAddDTO) {
        return toR(examService.questionAdd(examQuestionAddDTO));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取竞赛信息", description = "获取竞赛信息以及竞赛内题目信息")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public R<ExamDetailVO> detail(Long examId) {
        return R.ok(examService.detail(examId));
    }

    @PutMapping("/edit")
    @Operation(summary = "编辑竞赛信息", description = "根据传递参数编辑竞赛信息")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3004", description = "竞赛已经存在")
    @ApiResponse(responseCode = "3201", description = "竞赛开始时间不能早于当前时间")
    @ApiResponse(responseCode = "3202", description = "竞赛开始时间不能晚于结束时间")
    @ApiResponse(responseCode = "3205", description = "竞赛已经开始")
    public R<Void> edit(@RequestBody ExamEditDTO examEditDTO) {

        return toR(examService.edit(examEditDTO));
    }

    @DeleteMapping("/question/delete")
    @Operation(summary = "删除题目", description = "在相关竞赛中删除题目")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3203", description = "竞赛不存在")
    @ApiResponse(responseCode = "3205", description = "竞赛已经开始")
    @ApiResponse(responseCode = "3208", description = "竞赛已经发布不能进行编辑删除等操作")
    public R<Void> questionDelete(Long examId, Long questionId) {
        return toR(examService.questionDelete(examId, questionId));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除竞赛", description = "删除竞赛并且删除竞赛相关题目")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3203", description = "竞赛不存在")
    @ApiResponse(responseCode = "3205", description = "竞赛已经开始")
    public R<Void> delete(Long examId) {
        return toR(examService.delete(examId));
    }

    @PutMapping("/publish")
    @Operation(summary = "发布竞赛", description = "根据竞赛id来发布竞赛")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3203", description = "竞赛不存在")
    @ApiResponse(responseCode = "3206", description = "竞赛不包含题目")
    @ApiResponse(responseCode = "3207", description = "竞赛已经结束不能进行操作")
    public R<Void> publish(Long examId) {
        return toR(examService.publish(examId));
    }

    @PutMapping("/cancelPublish")
    @Operation(summary = "撤销发布竞赛", description = "根据竞赛id来撤销发布竞赛")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3203", description = "竞赛不存在")
    @ApiResponse(responseCode = "3206", description = "竞赛不包含题目")
    public R<Void> cancelPublish(Long examId) {
        return toR(examService.cancelPublish(examId));
    }
}
