package com.zcit.bill.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcit.bill.bill.entity.Bill;
import com.zcit.bill.bill.mapper.BillMapper;
import com.zcit.bill.bill.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcit.bill.billmember.entity.BillMember;
import com.zcit.bill.billmember.mapper.BillMemberMapper;
import com.zcit.bill.billmember.service.IBillMemberService;
import com.zcit.bill.member.entity.Member;
import com.zcit.bill.member.service.IMemberService;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.BaseReturn;
import com.zcit.common.enums.BillMemberType;
import com.zcit.common.enums.Delete;
import com.zcit.common.enums.FirstType;
import com.zcit.common.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 金额流水表 服务实现类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    @Autowired
    IBillService billService;
    @Autowired
    IBillMemberService billMemberService;
    @Autowired
    IMemberService memberService;
    @Autowired
    BillMapper billMapper;
    @Autowired
    BillMemberMapper billMemberMapper;

    /**
     *  新增/修改流水
     */
    @Override
    public void addOrUpdate(Bill bill) throws Exception {

        if(!StringUtils.isEmpty(bill.getId())) {
            //如果是成员的缴费、取出信息，不允许修改删除
            Bill bill1 = billMapper.selectById(bill.getId());
            if (bill1.getFirstConsumptionId().equals(FirstType.PAY.toString())) {
                throw new MyException("成员缴费流水不能修改和删除");
            }
        }

        //新建一个map，记录会员id和要支付的金额，方便后面批量更新
        Map<String,BigDecimal> map = new HashMap<>();
        List<BillMember> billMemberList = new ArrayList<>();
        //判断分担开支的总额是否和金额一致
        System.out.println("---------------"+bill.getMoney());
        BigDecimal totalMoney = new BigDecimal(0);
        for(BillMember billMember:bill.getPayMemberList()){
            if(billMember.getType() == BillMemberType.PERCENT.getCode()){
                BigDecimal temp2 = new BigDecimal(String.valueOf(0.01));
                BigDecimal temp3 = billMember.getTypeNumber().multiply(temp2);
                BigDecimal temp4 = temp3.multiply(bill.getMoney());
                //bigdecimal的加法需要一个值去接收
                totalMoney = totalMoney.add(temp4);
                map.put(billMember.getMemberId(),temp4);

                billMember.setMoney(temp4);
                billMember.preInsert();
                billMemberList.add(billMember);
            }else{
                totalMoney = totalMoney.add(billMember.getTypeNumber());
                map.put(billMember.getMemberId(),billMember.getTypeNumber());

                billMember.setMoney(billMember.getTypeNumber());
                billMember.preInsert();
                billMemberList.add(billMember);
            }
        }
        System.out.println("---------------"+totalMoney);
        if(bill.getMoney().compareTo(totalMoney) != 0){
            throw new MyException("成员分担的金额和总金额不一致");
        }

        //先插一条总消费
        if(StringUtils.isEmpty(bill.getId())){
            bill.preInsert();
        }else{
            bill.preUpdate();
            //先把要删除的对应成员的金额还进去
            recover(bill.getId());
            //再删除之前对应的成员分担流水
            billMemberService.remove(new QueryWrapper<BillMember>().eq(BillMember.BILL_ID,bill.getId()));
        }
        billService.saveOrUpdate(bill);

        List<BillMember> billMemberNewList = new ArrayList<>();
        List<String> memberIdList = new ArrayList<>();
        for(BillMember billMember:billMemberList){
            memberIdList.add(billMember.getMemberId());
            billMember.setBillId(bill.getId());
            billMemberNewList.add(billMember);
        }

        //根据会员id查出每个成员信息
        List<Member> memberList = memberService.list(new QueryWrapper<Member>()
                .in(BaseEntity.ID, memberIdList)
                .eq(BaseEntity.DELETED, Delete.UNDELETED.getCode()));
        //循环遍历并修改每个成员金额，然后批量更新
        List<Member> memberNewList = new ArrayList<>();
        for(Member member:memberList){
            member.setMoney(member.getMoney().subtract(map.get(member.getId())));
            memberNewList.add(member);
        }
        System.out.println(memberNewList);
        memberService.updateBatchById(memberNewList);

        //插入成员的流水表(billmember)
        billMemberService.saveBatch(billMemberNewList);
    }

    /**
     *  恢复要删除的成员流水和对应金额
     */
    public void recover(String id){
        List<BillMember> list = billMemberService.list(new QueryWrapper<BillMember>().eq(BillMember.BILL_ID, id));
        Map<String,BigDecimal> deleteMap = new HashMap<>();
        List<String> memberIdList = new ArrayList<>();
        for(BillMember billMember:list){
            memberIdList.add(billMember.getMemberId());
            deleteMap.put(billMember.getMemberId(),billMember.getMoney());
        }
        //根据会员id查出每个成员信息
        List<Member> memberList = memberService.list(new QueryWrapper<Member>()
                .in(BaseEntity.ID,memberIdList)
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode()));
        //循环遍历并修改每个成员金额，然后批量更新
        List<Member> memberNewList = new ArrayList<>();
        for(Member member:memberList){
            member.setMoney(member.getMoney().add(deleteMap.get(member.getId())));
            memberNewList.add(member);
        }
        memberService.updateBatchById(memberNewList);
    }

    /**
     *  该账本流水分页展示
     */
    @Override
    public IPage<Bill> pageBill(Integer current, Integer size, String registerId,String secondConsumptionName ,String firstConsumptionId) throws Exception {
        IPage<Bill> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        List<Bill> bills = billMapper.pageBill(page, registerId, secondConsumptionName , firstConsumptionId);
        return page.setRecords(bills);
    }

    /**
     *  根据id获得该条流水
     */
    @Override
    public Bill findBillById(String id) throws Exception {
        Bill bill = billMapper.selectById(id);
        List<BillMember> list = billMemberMapper.findInfo(id);
        bill.setPayMemberList(list);
        return bill;
    }

    /**
     *  删除指定id的流水
     */
    @Override
    public void deleteById(String id) {
        //如果是成员的缴费、取出信息，不允许修改删除
        Bill bill = billMapper.selectById(id);
        if(bill.getFirstConsumptionId().equals(FirstType.PAY.toString())){
            throw new MyException("成员缴费流水不能修改和删除");
        }
        //先恢复要删除的对应id的成员流水金额
        recover(id);
        billMapper.delete(new QueryWrapper<Bill>()
                .eq(BaseEntity.ID,id));
        billMemberMapper.delete(new QueryWrapper<BillMember>()
                .eq(BillMember.BILL_ID,id));
    }
}
