package com.zcit.bill.secondconsumption.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zcit.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 二级消费类型表
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_second_consumption")
public class SecondConsumption extends BaseEntity<SecondConsumption,String> {

    /**
     * 二级消费类型名称
     */
    private String name;

    /**
     * 一级消费类型id
     */
    private String firstConsumptionId;

    /**
     * 默认0，为1则表明是内置消费类型，不能被修改、删除
     */
    private Integer root;


    public static final String NAME = "name";

    public static final String ROOT = "root";

    public static final String FIRST_CONSUMPTION_ID = "first_consumption_id";

}
