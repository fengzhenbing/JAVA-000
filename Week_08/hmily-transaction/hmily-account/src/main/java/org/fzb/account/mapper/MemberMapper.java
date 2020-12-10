package org.fzb.account.mapper;

import org.apache.ibatis.annotations.Update;
import org.fzb.account.entity.MemberDO;

/**
 * MemberMapper
 *
 * @author fengzhenbing
 */
public interface MemberMapper {

    @Update("update member set balance = balance - #{buyMoney} where id =#{id} and balance - #{buyMoney} > 0 ")
    int decreaseBalance(MemberDO memberDO);
}
