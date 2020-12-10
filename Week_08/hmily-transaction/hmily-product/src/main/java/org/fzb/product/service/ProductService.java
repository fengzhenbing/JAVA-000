package org.fzb.product.service;

import org.fzb.product.entity.ProductDO;

public interface ProductService {

   Boolean decreaseStock(ProductDO productDO);
}
