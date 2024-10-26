package com.yun.common.security.handler;

import com.yun.common.core.domain.R;
import com.yun.common.core.enums.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
//@Slf4j
public class GlobalExceptionHandler {
    /**
     * 请求方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?>handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // log.error("请求地址'{}',不⽀持'{}'请求", requestURI, e.getMethod());
        return R.fail(ResultCode.ERROR);
    }

    /**
     * 拦截运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R<?> handleRuntimeException(RuntimeException e, HttpServletRequest
            request) {
        String requestURI = request.getRequestURI();
        // log.error("请求地址'{}',发⽣异常.", requestURI, e);
        return R.fail(ResultCode.ERROR);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // log.error("请求地址'{}',发⽣异常.", requestURI, e);
        return R.fail(ResultCode.ERROR);
    }
}
