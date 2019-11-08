package com.zcit.common.enums;

import lombok.Getter;

/**
 * 消费类型权限枚举
 * Created by huangyifeng on 2018/9/24.
 */
@Getter
public enum ConsumptionRootType {
    ALLOW(0, "允许删除修改"),
    REFUSE(1, "不允许删除修改");

    private int code;

    private String desc;

    ConsumptionRootType(int code, String desc) {
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
        ConsumptionRootType[] values = ConsumptionRootType.values();
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
