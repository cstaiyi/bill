package com.zcit.bill.bill.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zcit.bill.billmember.entity.BillMember;
import com.zcit.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 金额流水表
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_bill")
public class Bill extends BaseEntity<Bill,String> {

    //支付成员名称
    @TableField(exist = false)
    private String payName;

    //一级消费类型表名称
    @TableField(exist = false)
    private String firstConsumptionName;

    //分担开支的成员列表
    @TableField(exist = false)
    private List<BillMember> payMemberList;

    /**
     * 账本账号id
     */
    private String registerId;

    /**
     * 流水发生时间
     */
    private Date billDate;

    /**
     * 一级消费类型表id
     */
    private String firstConsumptionId;

    /**
     * 二级消费类型表名称
     */
    private String secondConsumptionName;

    /**
     * 出钱金额
     */
    private BigDecimal money;

    /**
     * 备注
     */
    private String mark;

    /**
     * 支付方式
     */
    private String style;

    public static final String REGISTER_ID = "register_id";

    public static final String BILL_DATE = "bill_date";

    public static final String FIRST_CONSUMPTION_ID = "first_consumption_id";

    public static final String SECOND_CONSUMPTION_NAME = "second_consumption_name";

    public static final String MONEY = "money";

    public static final String MARK = "mark";

    public static final String STYLE = "style";
}
