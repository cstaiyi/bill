package com.zcit.bill.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcit.bill.member.entity.Member;
import com.zcit.bill.member.service.IMemberService;
import com.zcit.common.base.BaseEntity;
import com.zcit.common.base.BaseReturn;
import com.zcit.common.base.DataReturn;
import com.zcit.common.base.PageResult;
import com.zcit.common.enums.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 成员表 前端控制器
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    IMemberService memberService;

    /**
     *  账本添加或修改成员
     */
    @PostMapping("/addOrUpdate")
    public BaseReturn add(@RequestBody Member member) throws Exception{
        memberService.add(member);
        return BaseReturn.success("操作成功");
    }

    /**
     *  删除成员
     */
    @PostMapping("/delete")
    public BaseReturn delete(@RequestBody Member member){
        member.setDeleted(Delete.DELETED.getCode());
        member.preUpdate();
        memberService.updateById(member);
        return BaseReturn.success("删除成功");
    }

    /**
     *  分页查询该账本下的所有成员
     */
    @GetMapping("/pageMember")
    public BaseReturn pageBillUserInfo(@RequestParam Integer current, @RequestParam Integer size, @RequestParam String registerId) throws Exception{
        IPage<Member> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return new PageResult<>(memberService.page(page,new QueryWrapper<Member>()
                .eq(Member.REGISTER_ID, registerId)
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode())
                .orderByDesc(BaseEntity.CREATE_TIME)));
    }

    /**
     *  获取账本下所有成员列表
     */
    @GetMapping("/findMemberList")
    public BaseReturn findMemberList(@RequestParam String id){
        return new DataReturn<>(memberService.list(new QueryWrapper<Member>()
                .eq(Member.REGISTER_ID,id)
                .eq(BaseEntity.DELETED,Delete.UNDELETED.getCode())));
    }
}

