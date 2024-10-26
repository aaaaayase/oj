package com.yun.system.controller;

import com.yun.common.core.domain.R;
import com.yun.system.domain.LoginDTO;
import com.yun.system.domain.SysUserSaveDTO;
import com.yun.system.domain.SysUserVO;
import com.yun.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yun
 * @date 2024/10/16 9:16
 * @desciption: 管理员相关接口
 */
@Tag(name = "管理员相关接口")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Operation(summary = "管理员登录", description = "根据账号以及密码来进行登录")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙请稍后重试")
    @ApiResponse(responseCode = "3102", description = "用户不存在")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginDTO loginDTO) {

        return sysUserService.login(loginDTO.getUserAccount(), loginDTO.getPassword());
    }

    @Operation(summary = "新增管理员", description = "根据提供的信息新增管理员⽤⼾")
    @PostMapping("/add")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "⽤⼾已存在")
    public R<Void> add(@RequestBody SysUserSaveDTO saveDTO) {
        return null;
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除⽤⼾", description = "通过⽤⼾id删除⽤⼾")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.PATH, description = "⽤⼾ID")
    })
    @ApiResponse(responseCode = "1000", description = "成功删除⽤⼾")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
    public R<Void> delete(@PathVariable Long userId) {
        return null;
    }

    //修改我就不演⽰了和新增差不多

    @Operation(summary = "⽤⼾详情", description = "根据查询条件查询⽤⼾详情")
    @GetMapping("/detail")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.QUERY, description = "⽤⼾ID"),
            @Parameter(name = "sex", in = ParameterIn.QUERY, description = "⽤⼾性别")
    })
    @ApiResponse(responseCode = "1000", description = "成功获取⽤⼾信息")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
    public R<SysUserVO> detail(Long userId, @RequestParam(required = false) String sex) {
        return null;
    }


}
