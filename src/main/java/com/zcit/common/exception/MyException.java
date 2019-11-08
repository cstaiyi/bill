package com.zcit.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常
 *
 * @author hifeng
 * @date 2018/8/7 14:57
 */
@Getter
@Setter
public class MyException extends RuntimeException {
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    private String msg;

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }
}
