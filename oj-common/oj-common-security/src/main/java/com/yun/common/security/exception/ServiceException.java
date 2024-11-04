package com.yun.common.security.exception;

import com.yun.common.core.enums.ResultCode;
import lombok.Getter;

/**
 * @author yun
 * @date 2024/11/4 10:29
 * @desciption: oj-system服务层业务异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
