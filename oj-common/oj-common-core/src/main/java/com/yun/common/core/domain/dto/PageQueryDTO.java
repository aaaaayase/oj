package com.yun.common.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/9 10:03
 * @desciption: 分页操作需要的基础属性
 */
@Getter
@Setter
public class PageQueryDTO {
    private Integer pageSize = 10; // 数量 必传

    private Integer pageNum = 1; // 页码
}
