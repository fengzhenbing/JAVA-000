package org.fzb.account.service;

import org.fzb.account.entity.MemberDO;

/**
 * MemberService
 *
 * @author fengzhenbing
 */
public interface MemberService {
    Boolean decreaseBalance(MemberDO memberDO);
}
