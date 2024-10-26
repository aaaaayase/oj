package com.yun.common.security.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/10/26 9:51
 * @desciption: 用户类
 */
@Getter
@Setter
public class LoginUser {
    private Integer identity; // 1 表示普通用户 2 表示管理员用户
}
