package com.yun.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yun
 * @date 2024/10/26 10:37
 * @desciption: 用户身份枚举
 */
@AllArgsConstructor
@Getter
public enum UserIdentity {

    ORDINARY(1, "普通用户"),
    ADMIN(2, "管理员");

    private int value;

    private String des;

}
