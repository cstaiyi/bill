package com.zcit.bill.register.service;

import com.zcit.bill.register.entity.Register;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author huangpengyi
 * @since 2019-07-20
 */
public interface IRegisterService extends IService<Register> {

    /**
     *  新增或修改账本账号
     */
    void add(Register register) throws Exception;

    /**
     *  平台登录
     */
    Register login(Register register) throws Exception;
}
