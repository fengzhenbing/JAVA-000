package org.fzb.product.service.impl;

import org.fzb.product.entity.ProductDO;
import org.fzb.product.mapper.ProductMapper;
import org.fzb.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 *
 * @author fengzhenbing
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Autowired(required = false)
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Boolean decreaseStock(ProductDO productDO) {
        return this.productMapper.decreaseStock(productDO) > 0;
    }
}
