package com.zcit.common.enums;

import lombok.Getter;

/**
 * 缴费枚举
 * Created by huangyifeng on 2018/9/24.
 */
@Getter
public enum PayType {
    PAY(1, "缴纳"),
    FETCH(2, "取出");

    private int code;

    private String desc;

    PayType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据编码获取描述
     *
     * @param code
     * @return
     */
    public static String toString(int code) {
        PayType[] values = PayType.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].code == code) {
                return values[i].desc;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
