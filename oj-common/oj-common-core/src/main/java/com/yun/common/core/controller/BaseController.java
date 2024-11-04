package com.yun.common.core.controller;

import com.yun.common.core.domain.R;

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

}
