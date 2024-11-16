package com.yun.common.core.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/10/26 9:51
 * @desciption: 登录者
 */
@Getter
@Setter
public class LoginUser {
    private String nickName;// 昵称

    private Integer identity; // 1 表示普通用户 2 表示管理员用户

    private String headImage; // 客户端用户的头像
}
