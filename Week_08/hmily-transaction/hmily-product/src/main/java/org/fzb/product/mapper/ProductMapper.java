package org.fzb.product.mapper;

import org.apache.ibatis.annotations.Update;
import org.fzb.product.entity.ProductDO;

/**
 * ProductMapper
 *
 * @author fengzhenbing
 */
public interface ProductMapper {

    @Update("update product set stock = stock - #{stock} where stock - #{stock} >0 and id = #{id}")
    int decreaseStock(ProductDO productDO);
}
