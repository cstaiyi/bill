package com.zcit.bill.bill.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcit.bill.bill.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 金额流水表 Mapper 接口
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
public interface BillMapper extends BaseMapper<Bill> {

    /**
     *  该账本流水分页展示
     */
    List<Bill> pageBill(IPage<Bill> page,@Param("registerId") String registerId,@Param("secondConsumptionName")String secondConsumptionName ,@Param("firstConsumptionId")String firstConsumptionId) throws Exception;

}
