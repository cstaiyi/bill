package com.zcit.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hifeng
 * @date 2018/8/1 16:07
 */
@Getter
@Setter
@ToString
public class DataReturn<T> extends BaseReturn {
    private T data;

    public DataReturn() {
    }

    public DataReturn(T data) {
        this.data = data;
    }

    public DataReturn(String message, T data) {
        super(message);
        this.data = data;
    }
}
