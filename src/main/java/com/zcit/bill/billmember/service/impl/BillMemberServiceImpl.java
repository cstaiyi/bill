package com.zcit.bill.billmember.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcit.bill.billmember.entity.BillMember;
import com.zcit.bill.billmember.mapper.BillMemberMapper;
import com.zcit.bill.billmember.service.IBillMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 金额流水分担中间表 服务实现类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Service
public class BillMemberServiceImpl extends ServiceImpl<BillMemberMapper, BillMember> implements IBillMemberService {

    @Autowired
    BillMemberMapper billMemberMapper;

    /**
     *  根据会员id获得会员所有流水分页列表
     */
    @Override
    public IPage<BillMember> pageBillMember(Integer current, Integer size, String id) throws Exception {
        IPage<BillMember> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        List<BillMember> billMembers = billMemberMapper.pageBillMember(page, id);
        return page.setRecords(billMembers);
    }

    /**
     * 按日期查询总额
     * @return
     * @throws Exception
     */
    @Override
    public List<BillMember> ListSum(String billDate,String secondConsumptionName ,String firstConsumptionId) throws Exception {
        return baseMapper.listSum(billDate,secondConsumptionName,firstConsumptionId);
    }

    /**
     * 消费总额
     * @return
     * @throws Exception
     */
    @Override
    public List<BillMember> ListAll(String secondConsumptionName ,String firstConsumptionId) throws Exception {
        return baseMapper.listAll(secondConsumptionName,firstConsumptionId);
    }
}
