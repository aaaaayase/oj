package com.yun.friend.controller.exam;

import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.service.exam.IExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yun
 * @date 2024/11/15 15:30
 * @desciption: 客户端竞赛相关接口
 */
@Tag(name = "客户端竞赛相关接口")
@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    @Autowired
    private IExamService examService;

    @GetMapping("/semiLogin/list")
    @Operation(summary = "获取竞赛列表", description = "传递参数获取满足条件的竞赛列表")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        return getTableDataInfo(examService.list(examQueryDTO));
    }

    @GetMapping("/semiLogin/redis/list")
    public TableDataInfo redisList(ExamQueryDTO examQueryDTO){
        return examService.redisList(examQueryDTO);
    }

}