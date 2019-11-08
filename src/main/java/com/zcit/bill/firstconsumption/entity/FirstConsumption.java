package com.zcit.bill.firstconsumption.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcit.bill.secondconsumption.entity.SecondConsumption;
import com.zcit.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 一级消费类型表
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_first_consumption")
public class FirstConsumption extends BaseEntity<FirstConsumption,String> {

    //对应的二级消费类型的列表
    @TableField(exist = false)
    private List<SecondConsumption> secondList;

    /**
     * 一级消费类型名称
     */
    private String name;

    /**
     * 默认0，为1则表明是内置消费类型，不能被修改、删除
     */
    private Integer root;

    /**
     * 账本账号id
     */
    private String registerId;

    public static final String NAME = "name";

    public static final String ROOT = "root";

    public static final String REGISTER_ID = "register_id";
}
