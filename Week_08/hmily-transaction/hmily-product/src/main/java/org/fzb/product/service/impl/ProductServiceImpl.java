package org.fzb.product.service.impl;

import org.dromara.hmily.annotation.HmilyTCC;
import org.fzb.common.entity.ProductDO;
import org.fzb.product.mapper.ProductMapper;
import org.fzb.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @HmilyTCC(confirmMethod="confirm",cancelMethod = "cancel")
    // @Transactional(rollbackFor = Exception.class)
    public Boolean decreaseStock(ProductDO productDO) {
        //冻结并减库存
        return productMapper.decreaseStock(productDO) > 0;
    }

    public void confirm(ProductDO productDO){
        // 撤销冻结
        productMapper.confirm(productDO);
    }

    public void cancel(ProductDO productDO){
        // 撤销冻结及库存
        productMapper.cancel(productDO);
    }
}
