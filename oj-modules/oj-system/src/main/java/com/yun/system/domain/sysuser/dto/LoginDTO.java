package com.yun.system.domain.sysuser.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yun
 * @date 2024/10/16 9:44
 * @desciption: 登录信息实体类
 */
@Data
public class LoginDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String userAccount;

    @NotNull
    @NotBlank
    @NotEmpty
    private String password;
}
