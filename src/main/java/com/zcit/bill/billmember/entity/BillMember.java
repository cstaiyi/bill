package com.zcit.bill.billmember.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcit.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 金额流水分担中间表
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_bill_member")
public class BillMember extends BaseEntity<BillMember,String> {

    //总流水备注
    @TableField(exist = false)
    private String mark;

    //消费总金额
    @TableField(exist = false)
    private String billMoney;

    //消费日期
    @TableField(exist = false)
    private String billDate;

    //一级消费类型名称
    @TableField(exist = false)
    private String firstConsumptionName;

    //二级消费类型名称
    @TableField(exist = false)
    private String secondConsumptionName;

    //一级消费类型ID
    @TableField(exist = false)
    private String firstConsumptionId;

    //支付类型名称
    @TableField(exist = false)
    private String typeName;

    //支付类型数量
    @TableField(exist = false)
    private String typeNumberName;

    //成员名称
    @TableField(exist = false)
    private String name;

    /**
     * 金额流水表id
     */
    private String billId;

    /**
     * 成员id
     */
    private String memberId;

    /**
     * 1 按比例分担 2 按具体金额分担
     */
    private Integer type;

    /**
     * 分担时填写的数量
     */
    private BigDecimal typeNumber;

    /**
     * 实际分担的金额
     */
    private BigDecimal money;

    @TableField(exist = false)
    private BigDecimal sumMoney;

    public static final String BILL_ID = "bill_id";

    public static final String MEMBER_ID = "member_id";

    public static final String TYPE = "type";

    public static final String TYPE_NUMBER = "type_number";

    public static final String MONEY = "money";

}
