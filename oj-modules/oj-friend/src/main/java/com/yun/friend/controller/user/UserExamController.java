package com.yun.friend.controller.user;

import com.yun.common.core.constants.HttpConstants;
import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.aspect.CheckUserStatus;
import com.yun.friend.domain.exam.dto.ExamDTO;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.service.user.IUserExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yun
 * @date 2024/11/17 9:43
 * @desciption: 用户与竞赛关系相关接口
 */
@RestController
@RequestMapping("/user/exam")
public class UserExamController extends BaseController {

    @Autowired
    private IUserExamService userExamService;

    @CheckUserStatus
    @PostMapping("/enter")
    @Operation(summary = "报名竞赛", description = "传递参数建立用户与竞赛之间的关系")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3003", description = "资源不存在")
    @ApiResponse(responseCode = "3205", description = "竞赛已经开始，无法进行操作")
    @ApiResponse(responseCode = "3301", description = "用户已经报过名，无需重复报名")
    public R<Void> enter(@RequestHeader(HttpConstants.AUTHENTICATION) String token,@RequestBody ExamDTO examDTO) {
        return toR(userExamService.enter(token, examDTO.getExamId()));
    }

    @GetMapping("/list")
    @Operation(summary = "我的竞赛列表", description = "传递参数获取满足条件的竞赛列表")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        return userExamService.list(examQueryDTO);
    }

}
