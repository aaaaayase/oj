package com.yun.common.core.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yun
 * @date 2024/10/15 13:22
 * @desciption: 实体类基类
 */
@Data
public class BaseEntity {
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
}
