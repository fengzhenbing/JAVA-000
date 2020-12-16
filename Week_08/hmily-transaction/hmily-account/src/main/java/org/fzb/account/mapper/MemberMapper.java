package org.fzb.account.mapper;

import org.apache.ibatis.annotations.Update;
import org.fzb.common.entity.MemberDO;

/**
 * MemberMapper
 *
 * @author fengzhenbing
 */
public interface MemberMapper {

    /**
     * 付款
     * @param memberDO
     * @return
     */
    @Update("update member set balance = balance - #{buyMoney} , freezing_balance = freezing_balance + #{buyMoney} " +
            " where id = #{id} and balance - #{buyMoney} > 0 ")
    int decreaseBalance(MemberDO memberDO);

    @Update("update member set freezing_balance = freezing_balance - #{buyMoney} " +
            " where id = #{id} and freezing_balance - #{buyMoney} > 0 ")
    int confirm(MemberDO memberDO);

    @Update("update member set balance = balance + #{buyMoney} , freezing_balance = freezing_balance - #{buyMoney} " +
            "where id = #{id} and freezing_balance - #{buyMoney} > 0 ")
    int cancel(MemberDO memberDO);
}
