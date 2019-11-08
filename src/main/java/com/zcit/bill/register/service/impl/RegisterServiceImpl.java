package com.zcit.bill.register.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcit.bill.register.entity.Register;
import com.zcit.bill.register.mapper.RegisterMapper;
import com.zcit.bill.register.service.IRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.Constant;
import com.zcit.common.enums.Delete;
import com.zcit.common.exception.MyException;
import com.zcit.common.utils.HttpContextUtil;
import com.zcit.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author huangpengyi
 * @since 2019-07-20
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {

    @Autowired
    RegisterMapper registerMapper;

    /**
     *  新增或修改账本账号
     */
    @Override
    public void add(Register register) throws Exception {
        if(StringUtils.isEmpty(register.getId())){
            boolean verify = verify(register);
            if(verify){
                register.preInsert();
                registerMapper.insert(register);
            }
        }else{
            register.preUpdate();
            registerMapper.updateById(register);
        }
    }

    /**
     *  平台登录
     */
    @Override
    public Register login(Register register) throws Exception {
        // 查询用户
        Register reg = baseMapper.selectOne(new QueryWrapper<Register>()
                .eq(Register.USER, register.getUser())
                .eq(BaseEntity.DELETED, Delete.UNDELETED.getCode()));
        if (reg == null) {
            throw new MyException("用户不存在");
        }

        if (!reg.getPassword().equals(register.getPassword())) {
            throw new MyException("密码错误");
        }

        String token = IdUtil.uuid();
        HttpContextUtil.getHttpServletRequest().getSession().setAttribute(Constant.REG_TOKEN + reg.getId(), token);
        reg.setToken(token);
        reg.setPassword(null);
        return reg;
    }

    /**
     *  新增账号验证
     */
    public boolean verify(Register register){
        Register register1 = registerMapper.selectOne(new QueryWrapper<Register>()
                .eq(Register.USER, register.getUser()));
        if(register1 != null){
            throw new MyException("该账号已存在！");
        }
        return true;
    }

}
