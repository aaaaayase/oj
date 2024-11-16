package com.yun.friend.controller.user;

import com.yun.common.core.constants.HttpConstants;
import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.vo.LoginUserVO;
import com.yun.friend.domain.user.dto.UserDTO;
import com.yun.friend.service.user.IUserService;
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

    @PostMapping("/sendCode")
    @Operation(summary = "获取验证码", description = "前端传递参数到后端，后端需要将验证码发送到手机上")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3105", description = "输入的电话号有误")
    @ApiResponse(responseCode = "3106", description = "操作频繁，稍后重试")
    @ApiResponse(responseCode = "3107", description = "当天验证码请求次数已经达到上限")
    @ApiResponse(responseCode = "3108", description = "验证码发送错误")
    public R<Void> sendCode(@RequestBody UserDTO userDTO) {
        return toR(userService.sendCode(userDTO));
    }

    @PostMapping("/code/login")
    @Operation(summary = "登录", description = "根据前端传过来的验证码和手机号进行注册或登录")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3109", description = "验证码无效")
    @ApiResponse(responseCode = "3110", description = "验证码错误")
    public R<String> codeLogin(@RequestBody UserDTO userDTO) {
        return R.ok(userService.codeLogin(userDTO.getPhone(), userDTO.getCode()));
    }

    @DeleteMapping("/logout")
    @Operation(summary = "用户注销登录", description = "退出登录")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    public R<Void> logout(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return toR(userService.logout(token));
    }

    @Operation(summary = "获取用户信息", description = "获取用户的头像以及昵称信息返回给前端")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3000", description = "操作失败")
    @GetMapping("/info")
    public R<LoginUserVO> info(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return userService.info(token);
    }
}
