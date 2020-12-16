package org.fzb.order.feign;

import org.dromara.hmily.annotation.Hmily;
import org.fzb.common.entity.ProductDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("product")
public interface ProductClient {
    @PostMapping("/api/product/decreaseStock")
    @Hmily
    Boolean decreaseStock(@RequestBody ProductDO productDO);
}
