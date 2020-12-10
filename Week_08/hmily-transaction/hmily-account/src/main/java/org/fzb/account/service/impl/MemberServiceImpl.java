package org.fzb.account.service.impl;

import org.fzb.account.entity.MemberDO;
import org.fzb.account.mapper.MemberMapper;
import org.fzb.account.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MemberServiceImpl
 *
 * @author fengzhenbing
 */
@Service
public class MemberServiceImpl implements MemberService {

    private  final  MemberMapper memberMapper;

    @Autowired(required = false)
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Boolean decreaseBalance(MemberDO memberDO) {
        return  memberMapper.decreaseBalance(memberDO) > 0;
    }
}
