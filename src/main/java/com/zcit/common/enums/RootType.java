package com.zcit.common.enums;

import lombok.Getter;

/**
 * 超级管理员枚举
 * Created by huangyifeng on 2018/9/24.
 */
@Getter
public enum RootType {
    BILL(0, "账本账号"),
    ADMIN(1, "超级管理员账号");

    private int code;

    private String desc;

    RootType(int code, String desc) {
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
        RootType[] values = RootType.values();
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
