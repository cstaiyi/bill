package com.zcit.bill.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zcit.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 成员表
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_member")
public class Member extends BaseEntity<Member,String> {

    /**
     * 姓名
     */
    private String name;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 账本账号id
     */
    private String registerId;


    public static final String NAME = "name";

    public static final String MONEY = "money";

    public static final String REGISTER_ID = "register_id";

}
