package com.zcit.bill.firstconsumption.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcit.bill.firstconsumption.entity.FirstConsumption;
import com.zcit.bill.firstconsumption.mapper.FirstConsumptionMapper;
import com.zcit.bill.firstconsumption.service.IFirstConsumptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcit.bill.secondconsumption.entity.SecondConsumption;
import com.zcit.bill.secondconsumption.mapper.SecondConsumptionMapper;
import com.zcit.bill.secondconsumption.service.ISecondConsumptionService;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.enums.ConsumptionRootType;
import com.zcit.common.enums.Delete;
import com.zcit.common.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 一级消费类型表 服务实现类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Service
public class FirstConsumptionServiceImpl extends ServiceImpl<FirstConsumptionMapper, FirstConsumption> implements IFirstConsumptionService {

    @Autowired
    FirstConsumptionMapper firstConsumptionMapper;
    @Autowired
    ISecondConsumptionService secondConsumptionService;

    /**
     *  新增/修改对应账本账号下的一级消费类型
     */
    @Override
    public void add(FirstConsumption firstConsumption) throws Exception{
        if(StringUtils.isEmpty(firstConsumption.getId())){
            boolean single = single(firstConsumption);
            if(single){
                firstConsumption.preInsert();
                firstConsumptionMapper.insert(firstConsumption);
            }
        }else{
            //如果root为1，则不允许修改
            FirstConsumption firstConsumption1 = firstConsumptionMapper.selectById(firstConsumption.getId());
            if(firstConsumption1.getRoot() == ConsumptionRootType.REFUSE.getCode()){
                throw new MyException("内置消费类型，不允许修改");
            }
            firstConsumption.preUpdate();
            firstConsumptionMapper.updateById(firstConsumption);
            //删除之前的二级消费类型
            secondConsumptionService.remove(new QueryWrapper<SecondConsumption>()
                    .eq(SecondConsumption.FIRST_CONSUMPTION_ID,firstConsumption.getId()));
        }
        //插入二级消费类型----2019-7-22 18:30:47  考虑到二级消费类型可以随时删除，而且无法改名，流水记录表记录二级消费类型id改成记录二级消费类型的名称
        List<SecondConsumption> secondConsumptionList = new ArrayList<>();
        for(int i=0;i<firstConsumption.getSecondList().size();i++){
            SecondConsumption secondConsumption = new SecondConsumption();
            secondConsumption = firstConsumption.getSecondList().get(i);
            secondConsumption.setFirstConsumptionId(firstConsumption.getId());
            secondConsumption.preInsert();
            secondConsumptionList.add(secondConsumption);
        }
        secondConsumptionService.saveBatch(secondConsumptionList);

    }

    /**
     *  删除对应账本账号下的一级消费类型
     */
    @Override
    public void delete(FirstConsumption firstConsumption) throws Exception{
        //如果root为1，则不允许删除
        FirstConsumption firstConsumption1 = firstConsumptionMapper.selectById(firstConsumption.getId());
        if(firstConsumption1.getRoot() == ConsumptionRootType.REFUSE.getCode()){
            throw new MyException("内置消费类型，不允许删除");
        }
        //先删除对应的二级类型
        secondConsumptionService.remove(new QueryWrapper<SecondConsumption>()
                .eq(SecondConsumption.FIRST_CONSUMPTION_ID,firstConsumption.getId()));

        firstConsumption.setDeleted(Delete.DELETED.getCode());
        firstConsumption.preUpdate();
        firstConsumptionMapper.updateById(firstConsumption);
    }



    /**
     * 同账本下一级消费类型名称不能重复
     */
    public boolean single(FirstConsumption firstConsumption){
        FirstConsumption member1 = firstConsumptionMapper.selectOne(new QueryWrapper<FirstConsumption>()
                .eq(FirstConsumption.REGISTER_ID, firstConsumption.getRegisterId())
                .eq(FirstConsumption.NAME, firstConsumption.getName())
                .eq(BaseEntity.DELETED, Delete.UNDELETED.getCode()));

        if(member1 != null){
            throw new MyException("该名称已经有了");
        }

        return true;
    }

}
