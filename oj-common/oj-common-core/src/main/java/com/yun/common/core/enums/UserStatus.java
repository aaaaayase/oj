package com.yun.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yun
 * @date 2024/11/14 19:23
 * @desciption: 用户状态枚举
 */
@Getter
@AllArgsConstructor
public enum UserStatus {

    NORMAL(1),
    BLOCK(0);

    private Integer value;


}
