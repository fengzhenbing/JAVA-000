package org.fzb.product.controller;

import org.fzb.common.entity.ProductDO;
import org.fzb.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController
 *
 * @author fengzhenbing
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService memberService;

    @PostMapping("/decreaseStock")
    public Boolean decreaseStock(@RequestBody ProductDO productDO){
        return memberService.decreaseStock(productDO);
    }

}
