package org.fzb.order.feign;

import org.dromara.hmily.annotation.Hmily;
import org.fzb.common.entity.MemberDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author fengzhenbing
 */
@FeignClient("account")
public interface AccountClient {
    @PostMapping("/api/member/decreaseBalance")
    @Hmily
    Boolean decreaseBalance(@RequestBody MemberDO memberDO);
}
