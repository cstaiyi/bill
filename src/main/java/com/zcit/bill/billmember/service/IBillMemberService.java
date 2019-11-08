package com.zcit.bill.billmember.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcit.bill.billmember.entity.BillMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcit.bill.firstconsumption.entity.FirstConsumption;
import com.zcit.bill.secondconsumption.entity.SecondConsumption;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 金额流水分担中间表 服务类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
public interface IBillMemberService extends IService<BillMember> {

    /**
     *  根据会员id获得会员所有流水分页列表
     */
    IPage<BillMember> pageBillMember(Integer current, Integer size, String id) throws Exception;

    /**
     * 按日期查询总额
     * @return
     * @throws Exception
     */
    List<BillMember> ListSum(String billDate, String secondConsumptionName, String firstConsumptionId)throws Exception;

    /**
     * 消费总额
     * @return
     * @throws Exception
     */
    List<BillMember> ListAll(String secondConsumptionName, String firstConsumptionId)throws Exception;

}
