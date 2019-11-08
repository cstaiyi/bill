package com.zcit.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 基础返回对象
 *
 * @author hifeng
 * @date 2018/7/27 15:56
 */
@Getter
@Setter
@ToString
public class BaseReturn implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code = HttpStatus.OK.value();
    private String message = "操作成功";

    public BaseReturn() {
    }

    public BaseReturn(String message) {
        this.message = message;
    }

    public BaseReturn(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseReturn success() {
        return new BaseReturn();
    }

    public static BaseReturn success(String message) {
        return new BaseReturn(message);
    }

    public static BaseReturn error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统维护...");
    }

    public static BaseReturn error(String message) {
        return new BaseReturn(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static BaseReturn error(int code, String message) {
        return new BaseReturn(code, message);
    }
}
