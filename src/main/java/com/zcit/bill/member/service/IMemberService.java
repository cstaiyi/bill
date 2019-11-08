package com.zcit.bill.member.service;

import com.zcit.bill.member.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 成员表 服务类
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
public interface IMemberService extends IService<Member> {

    /**
     *  账本添加或修改成员
     */
    void add(Member member) throws Exception;
}
