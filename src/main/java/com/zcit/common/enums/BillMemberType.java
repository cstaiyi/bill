package com.zcit.common.enums;

import lombok.Getter;

/**
 * 分担类型枚举
 * Created by huangyifeng on 2018/9/24.
 */
@Getter
public enum BillMemberType {
    PERCENT(1, "比例分担"),
    MONEY(2, "具体金额分担");

    private int code;

    private String desc;

    BillMemberType(int code, String desc) {
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
        BillMemberType[] values = BillMemberType.values();
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
