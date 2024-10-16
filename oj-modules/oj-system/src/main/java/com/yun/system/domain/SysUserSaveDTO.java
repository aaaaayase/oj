package com.yun.system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author yun
 * @date 2024/10/16 13:34
 * @desciption:
 */
@Data
public class SysUserSaveDTO {
    @Schema()
    private String userAccount;
    private String password;

}
