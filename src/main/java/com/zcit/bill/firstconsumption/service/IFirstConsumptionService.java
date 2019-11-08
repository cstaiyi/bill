package com.zcit.bill.firstconsumption.service;

import com.zcit.bill.firstconsumption.entity.FirstConsumption;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 一级消费类型表 服务类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
public interface IFirstConsumptionService extends IService<FirstConsumption> {

    /**
     *  新增/修改对应账本账号下的一级消费类型
     */
    void add(FirstConsumption firstConsumption) throws Exception;

    /**
     *  删除对应账本账号下的一级消费类型
     */
    void delete(FirstConsumption firstConsumption) throws Exception;

}
