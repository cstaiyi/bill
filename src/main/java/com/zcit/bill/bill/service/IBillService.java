package com.zcit.bill.bill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcit.bill.bill.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 金额流水表 服务类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
public interface IBillService extends IService<Bill> {

    /**
     * 新增/修改流水
     */
    void addOrUpdate(Bill bill) throws Exception;

    /**
     * 该账本流水分页展示
     */
    IPage<Bill> pageBill(Integer current, Integer size, String registerId, String secondConsumptionName, String firstConsumptionId) throws Exception;

    /**
     * 根据id获得该条流水
     */
    Bill findBillById(String id) throws Exception;

    /**
     * 删除指定id的流水
     */
    void deleteById(String id);
}
