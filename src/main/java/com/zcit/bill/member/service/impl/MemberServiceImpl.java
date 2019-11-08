package com.zcit.bill.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcit.bill.bill.entity.Bill;
import com.zcit.bill.bill.mapper.BillMapper;
import com.zcit.bill.billmember.entity.BillMember;
import com.zcit.bill.billmember.mapper.BillMemberMapper;
import com.zcit.bill.member.entity.Member;
import com.zcit.bill.member.mapper.MemberMapper;
import com.zcit.bill.member.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.enums.*;
import com.zcit.common.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 成员表 服务实现类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    BillMemberMapper billMemberMapper;

    /**
     *  账本添加或修改成员
     */
    @Override
    public void add(Member member) throws Exception {
        if(StringUtils.isEmpty(member.getId())){
            boolean single = single(member);
            if(single){
                //新增成员默认金额为0
                member.setMoney(new BigDecimal(0));
                member.preInsert();
                memberMapper.insert(member);
            }
        }else{
            //检查一下金额是否有变动，如果有，就在流水和成员流水里面记录
            Member member1 = memberMapper.selectById(member.getId());
            Bill bill = new Bill();
//            if(member1.getMoney().compareTo(member.getMoney()) != 0){
            if(member.getMoney().compareTo(new BigDecimal(0)) != 0){
//                if(member1.getMoney().compareTo(member.getMoney()) == -1){
//                    //增加
//                    bill.setSecondConsumptionName(PayType.PAY.toString());
//                }else if(member1.getMoney().compareTo(member.getMoney()) == 1){
//                    //减少
//                    bill.setSecondConsumptionName(PayType.FETCH.toString());
//                }
                if(member.getMoney().compareTo(new BigDecimal(0)) == 1){
                    //缴纳
                    bill.setSecondConsumptionName(PayType.PAY.toString());
                }else if(member.getMoney().compareTo(new BigDecimal(0)) == -1){
                    //取出
                    if(member1.getMoney().compareTo(new BigDecimal(0)) == -1){
                        throw new MyException("已经超支，没有多余的钱可以取出了");
                    }
                    if(member1.getMoney().compareTo(member.getMoney().multiply(new BigDecimal(-1))) == -1){
                        throw new MyException("取出金额超过现有金额，无法取出");
                    }
                    bill.setSecondConsumptionName(PayType.FETCH.toString());
                }
                bill.setFirstConsumptionId(FirstType.PAY.toString());
                bill.setRegisterId(member.getRegisterId());
                bill.setBillDate(new Date());
//                BigDecimal temp = member.getMoney().subtract(member1.getMoney());
//                if(temp.compareTo(new BigDecimal(-1)) == -1){
//                    temp = temp.multiply(new BigDecimal(-1));
//                }
                bill.setMoney(member.getMoney());
                bill.preInsert();
                billMapper.insert(bill);
                //然后插入对应成员流水
                BillMember billMember = new BillMember();
                billMember.setBillId(bill.getId());
                billMember.setMemberId(member.getId());
                billMember.setType(BillMemberType.MONEY.getCode());
                billMember.setTypeNumber(member.getMoney());
                billMember.setMoney(member.getMoney());
                billMember.preInsert();
                billMemberMapper.insert(billMember);

                //更新金额
                member.setMoney(member1.getMoney().add(member.getMoney()));
            } else {
                throw new MyException("缴纳或取出的金额不能为0");
            }
            member.preUpdate();
            memberMapper.updateById(member);
        }
    }

    /**
     * 同账本下成员名称不能重复
     */
    public boolean single(Member member) throws Exception{
        Member member1 = memberMapper.selectOne(new QueryWrapper<Member>()
                .eq(Member.REGISTER_ID, member.getRegisterId())
                .eq(Member.NAME, member.getName())
                .eq(BaseEntity.DELETED, Delete.UNDELETED.getCode()));

        if(member1 != null){
            throw new MyException("成员名称不能相同");
        }

        return true;
    }

}
