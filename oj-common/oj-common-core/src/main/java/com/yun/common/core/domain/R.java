package com.yun.common.core.domain;

import com.yun.common.core.enums.ResultCode;
import lombok.Data;

import java.security.PublicKey;

/**
 * @author yun
 * @date 2024/10/16 9:11
 * @desciption: 统一返回值类型
 */
@Data
public class R<T> {

    private int code;
    private String msg;
    private T data;


    public static <T> R<T> ok() {
        return assembleResult(null, ResultCode.SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        return assembleResult(data, ResultCode.SUCCESS);
    }

    public static <T> R<T> fail() {
        return assembleResult(null, ResultCode.FAILED);
    }

    public static R<?> fail(int code, String msg) {
        return assembleResult(null, msg, code);
    }

    /**
     * 指定错误码
     *
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(ResultCode resultCode) {
        return assembleResult(null, resultCode);
    }

    /**
     * 组装结果
     *
     * @param data
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> R<T> assembleResult(T data, ResultCode resultCode) {
        R<T> r = new R<>();

        r.setCode(resultCode.getCode());
        r.setMsg(resultCode.getMsg());
        r.setData(data);

        return r;
    }

    public static <T> R<T> assembleResult(T data, String msg, int code) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);

        return r;
    }
}
