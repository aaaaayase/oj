package com.yun.system.domain.sysuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String userAccount;
    @Schema(description = "用户密码")
    private String password;

}
