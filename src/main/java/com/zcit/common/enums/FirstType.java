package com.zcit.common.enums;

import lombok.Getter;

/**
 * 内部消费类型枚举
 * Created by huangyifeng on 2018/9/24.
 */
@Getter
public enum FirstType {
    PAY(1, "133c7991ad5a11e98aff00163e0811ef");

    private int code;

    private String desc;

    FirstType(int code, String desc) {
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
        FirstType[] values = FirstType.values();
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
