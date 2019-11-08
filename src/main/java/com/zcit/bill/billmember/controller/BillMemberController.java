package com.zcit.bill.billmember.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcit.bill.bill.entity.Bill;
import com.zcit.bill.billmember.service.IBillMemberService;
import com.zcit.bill.firstconsumption.entity.FirstConsumption;
import com.zcit.bill.secondconsumption.entity.SecondConsumption;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.BaseReturn;
import com.zcit.common.base.DataReturn;
import com.zcit.common.base.PageResult;
import com.zcit.common.enums.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * <p>
 * 金额流水分担中间表 前端控制器
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@RestController
@RequestMapping("/bill-member")
public class BillMemberController {

    @Autowired
    IBillMemberService billMemberService;

    /**
     *  根据会员id获得会员所有流水分页列表
     */
    @GetMapping("/pageBillMember")
    public BaseReturn pageBillMember(@RequestParam Integer current,@RequestParam Integer size,@RequestParam String id) throws Exception{
        return new PageResult<>(billMemberService.pageBillMember(current,size,id));
    }

    /**
     * 按日期查询总额
     * @param billDate
     * @return
     * @throws Exception
     */
    @GetMapping("/ListSum")
    public BaseReturn ListSum(@RequestParam String billDate,@RequestParam(required = false) String secondConsumptionName ,@RequestParam(required = false)  String firstConsumptionId)throws Exception{
        return new DataReturn<>(billMemberService.ListSum(billDate,secondConsumptionName,firstConsumptionId));
    }

    /**
     * 消费总额
     * @return
     * @throws Exception
     */
    @GetMapping("/ListAll")
    public BaseReturn ListAll(@RequestParam(required = false) String secondConsumptionName ,@RequestParam(required = false)  String firstConsumptionId)throws Exception{
        return new DataReturn<>(billMemberService.ListAll(secondConsumptionName,firstConsumptionId));
    }

}

