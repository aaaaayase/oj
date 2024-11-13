package com.yun.system.controller.user;

import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.system.domain.user.dto.UserDTO;
import com.yun.system.domain.user.dto.UserQueryDTO;
import com.yun.system.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yun
 * @date 2024/11/13 11:24
 * @desciption: 客户端用户相关接口
 */
@Tag(name = "客户端相关接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    @Operation(summary = "获取客户端用户列表", description = "传递参数获取满足条件的客户端用户列表")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public TableDataInfo list(UserQueryDTO userQueryDTO) {
        return getTableDataInfo(userService.list(userQueryDTO));
    }

    @PutMapping("/updateStatus")
    @Operation(summary = "更新用户状态", description = "拉黑或解禁客户端用户")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3102", description = "用户不存在")
    public R<Void> updateStatus(@RequestBody UserDTO userDTO) {
        // TODO 拉黑：禁止用户操作   解禁：放开用户操作
        return toR(userService.updateStatus(userDTO));
    }

}
