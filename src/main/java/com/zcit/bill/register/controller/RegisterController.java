package com.zcit.bill.register.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcit.bill.register.entity.Register;
import com.zcit.bill.register.service.IRegisterService;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.BaseReturn;
import com.zcit.common.base.DataReturn;
import com.zcit.common.base.PageResult;
import com.zcit.common.enums.Delete;
import com.zcit.common.enums.RootType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author huangpengyi
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    IRegisterService registerService;

    /**
     *  平台登录
     */
    @PostMapping("/login")
    public BaseReturn login(@RequestBody Register register) throws Exception{
        return new DataReturn<>(registerService.login(register));
    }

    /**
     *  新增或修改账本账号
     */
    @PostMapping("/addOrUpdate")
    public BaseReturn add(@RequestBody Register register) throws Exception{
        registerService.add(register);
        return BaseReturn.success("操作成功");
    }

    /**
     * 分页查询所有账本账号
     */
    @GetMapping("/pageBillUserInfo")
    public BaseReturn pageBillUserInfo(@RequestParam Integer current, @RequestParam Integer size) throws Exception{
        IPage<Register> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return new PageResult<>(registerService.page(page,new QueryWrapper<Register>()
                .eq(Register.ROOT, RootType.BILL)
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode())
                .orderByDesc(BaseEntity.CREATE_TIME)));
    }

    /**
     *  删除账本账号
     */
    @PostMapping("/delete")
    public BaseReturn delete(@RequestBody Register register){
        register.setDeleted(Delete.DELETED.getCode());
        register.preUpdate();
        registerService.updateById(register);
        return BaseReturn.success("删除成功");
    }

}


















