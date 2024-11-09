package com.yun.common.core.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/4 9:11
 * @desciption: 用于接口的基础方法
 */
public class BaseController {

    public R<Void> toR(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }

    public R<Void> toR(boolean result) {
        return result ? R.ok() : R.fail();
    }

    public TableDataInfo getTableDataInfo(List<?> list) {
        if (CollectionUtil.isEmpty(list)) {
            return TableDataInfo.empty();
        }

        // pagehelper还会提前做好满足条件结果的总数的计数
        long total = new PageInfo<>(list).getTotal();
        return TableDataInfo.success(list, total);
    }

}
