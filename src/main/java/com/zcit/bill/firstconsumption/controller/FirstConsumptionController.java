package com.zcit.bill.firstconsumption.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcit.bill.firstconsumption.entity.FirstConsumption;
import com.zcit.bill.firstconsumption.service.IFirstConsumptionService;
import com.zcit.bill.secondconsumption.entity.SecondConsumption;
import com.zcit.bill.secondconsumption.service.ISecondConsumptionService;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.BaseReturn;
import com.zcit.common.base.DataReturn;
import com.zcit.common.base.PageResult;
import com.zcit.common.enums.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 一级消费类型表 前端控制器
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@RestController
@RequestMapping("/first-consumption")
public class FirstConsumptionController {

    @Autowired
    IFirstConsumptionService firstConsumptionService;
    @Autowired
    ISecondConsumptionService secondConsumptionService;

    /**
     *  新增/修改对应账本账号下的一级消费类型
     */
    @PostMapping("/addOrUpdate")
    public BaseReturn add(@RequestBody FirstConsumption firstConsumption) throws Exception{
        firstConsumptionService.add(firstConsumption);
        return BaseReturn.success("操作成功");
    }

    /**
     *  对应账本账号下的一级消费类型分页展示
     */
    @GetMapping("/pageFirstConsumption")
    public BaseReturn pageBillUserInfo(@RequestParam Integer current, @RequestParam Integer size, @RequestParam String registerId) throws Exception{
        IPage<FirstConsumption> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return new PageResult<>(firstConsumptionService.page(page,new QueryWrapper<FirstConsumption>()
                .eq(FirstConsumption.REGISTER_ID, registerId)
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode())
                .orderByDesc(BaseEntity.CREATE_TIME)));
    }

    /**
     *  删除对应账本账号下的一级消费类型
     */
    @PostMapping("/delete")
    public BaseReturn delete(@RequestBody FirstConsumption firstConsumption) throws Exception{
        firstConsumptionService.delete(firstConsumption);
        return BaseReturn.success("删除成功");
    }

    /**
     *  根据一级消费类型id获取二级消费类型
     */
    @GetMapping("/findSecondDetail")
    public BaseReturn findSecondDetail(@RequestParam String id){
        return new DataReturn<>(secondConsumptionService.list(new QueryWrapper<SecondConsumption>()
                .eq(SecondConsumption.FIRST_CONSUMPTION_ID,id)
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode())));
    }

    /**
     *  获取所有一级消费类型列表
     */
    @GetMapping("/findFirstList")
    public BaseReturn findFirstList(@RequestParam String id){
        return new DataReturn<>(firstConsumptionService.list(new QueryWrapper<FirstConsumption>()
                .eq(FirstConsumption.REGISTER_ID,id)
//                .or()
//                .eq(FirstConsumption.REGISTER_ID,"1")
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode())));
    }

}

