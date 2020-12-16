package org.fzb.account.service;


import org.fzb.common.entity.MemberDO;

/**
 * MemberService
 *
 * @author fengzhenbing
 */
public interface MemberService {
    Boolean decreaseBalance(MemberDO memberDO) throws Exception;
}
