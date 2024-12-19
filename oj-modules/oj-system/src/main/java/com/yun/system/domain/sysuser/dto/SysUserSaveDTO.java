package com.yun.system.domain.sysuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/10/16 13:34
 * @desciption:
 */
@Getter
@Setter
public class SysUserSaveDTO {
    @Schema(description = "用户账号")
    @NotNull
    @NotBlank
    @NotEmpty
    private String userAccount;

    @Schema(description = "用户密码")
    @NotNull
    @NotBlank
    @NotEmpty
    private String password;

    @Schema(description = "用户密码")
    @NotNull
    @NotBlank
    @NotEmpty
    private String nickname;

}
