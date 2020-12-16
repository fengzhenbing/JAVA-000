package org.fzb.product.service;


import org.fzb.common.entity.ProductDO;

public interface ProductService {

   Boolean decreaseStock(ProductDO productDO);
}
