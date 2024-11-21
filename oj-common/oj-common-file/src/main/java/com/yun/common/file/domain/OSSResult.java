package com.yun.common.file.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/18 17:58
 * @desciption:
 */
@Setter
@Getter
public class OSSResult {
    private String name;

    private boolean success; // 对象状态 true成功 false失败

}
