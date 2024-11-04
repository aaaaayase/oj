package com.yun.system.domain.dto;

import lombok.Data;

/**
 * @author yun
 * @date 2024/10/16 9:44
 * @desciption: 登录信息实体类
 */
@Data
public class LoginDTO {
    private String userAccount;
    private String password;
}
