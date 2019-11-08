package com.zcit.common.enums;

import lombok.Getter;

/**
 * 删除枚举
 * Created by huangyifeng on 2018/9/24.
 */
@Getter
public enum Delete {
    UNDELETED(0, "未删除"),
    DELETED(1, "已删除");

    private int code;

    private String desc;

    Delete(int code, String desc) {
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
        Delete[] values = Delete.values();
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
