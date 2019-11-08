package com.zcit.bill.billmember.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcit.bill.billmember.entity.BillMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 金额流水分担中间表 Mapper 接口
 * </p>
 *
 * @author luf
 * @since 2019-07-22
 */
public interface BillMemberMapper extends BaseMapper<BillMember> {

    /**
     *  根据id获得该条流水
     */
    List<BillMember> findInfo(@Param("id") String id) throws Exception;

    /**
     *  根据会员id获得会员所有流水分页列表
     */
    List<BillMember> pageBillMember(IPage<BillMember> page,@Param("id") String id);

    /**
     * 按日期查询总额
     * @param billDate
     * @return
     * @throws Exception
     */
    List<BillMember> listSum(@Param("billDate") String billDate,@Param("secondConsumptionName")String secondConsumptionName ,@Param("firstConsumptionId")String firstConsumptionId) throws Exception;

    /**
     * 消费总额
     * @return
     * @throws Exception
     */
    List<BillMember> listAll(@Param("secondConsumptionName")String secondConsumptionName ,@Param("firstConsumptionId")String firstConsumptionId) throws Exception;
}
