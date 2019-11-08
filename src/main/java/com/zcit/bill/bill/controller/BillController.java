package com.zcit.bill.bill.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcit.bill.bill.entity.Bill;
import com.zcit.bill.bill.service.IBillService;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.BaseReturn;
import com.zcit.common.base.DataReturn;
import com.zcit.common.base.PageResult;
import com.zcit.common.enums.Delete;
import com.zcit.common.enums.RootType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 金额流水表 前端控制器
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    IBillService billService;

    /**
     *  新增/修改流水
     */
    @PostMapping("/addOrUpdate")
    public BaseReturn addOrUpdate(@RequestBody Bill bill) throws Exception{
        billService.addOrUpdate(bill);
        return BaseReturn.success("操作成功");
    }

    /**
     *  该账本流水分页展示
     */
    @GetMapping("/pageBill")
    public BaseReturn pageBill(@RequestParam Integer current, @RequestParam Integer size, @RequestParam String registerId,@RequestParam(required = false) String secondConsumptionName ,@RequestParam(required = false)  String firstConsumptionId) throws Exception{
        return new PageResult<>(billService.pageBill(current,size,registerId,secondConsumptionName ,firstConsumptionId));
    }

    /**
     *  根据id获得该条流水
     */
    @GetMapping("/findBillById")
    public BaseReturn findBillById(@RequestParam String id) throws Exception{
        return new DataReturn<>(billService.findBillById(id));
    }

    /**
     *  删除指定id的流水
     */
    @GetMapping("/deleteById")
    public BaseReturn deleteById(@RequestParam String id) throws Exception{
        billService.deleteById(id);
        return BaseReturn.success("删除成功");
    }
}

