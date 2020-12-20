package org.fzb.account.service.impl;

import org.dromara.hmily.annotation.HmilyTCC;
import org.fzb.account.mapper.MemberMapper;
import org.fzb.account.service.MemberService;
import org.fzb.common.entity.MemberDO;
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
    @HmilyTCC(confirmMethod="confirm",cancelMethod = "cancel")
    // @Transactional(rollbackFor = Exception.class)
    public Boolean decreaseBalance(MemberDO memberDO) throws Exception {
          int i = 2/0;
        // 付款并冻结
       return  memberMapper.decreaseBalance(memberDO) > 0;
    }

    public void confirm(MemberDO memberDO){
        // 撤销冻结
        memberMapper.confirm(memberDO);
    }

    public void cancel(MemberDO memberDO){
        // 撤销冻结及付款
        memberMapper.cancel(memberDO);
    }
}
