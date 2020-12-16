package org.fzb.account.controller;

import org.fzb.account.service.MemberService;
import org.fzb.common.entity.MemberDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberController
 *
 * @author fengzhenbing
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/decreaseBalance")
    public Boolean decreaseBalance(@RequestBody MemberDO memberDO) throws Exception {
        return memberService.decreaseBalance(memberDO);
    }

}
