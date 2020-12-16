package org.fzb.product.mapper;

import org.apache.ibatis.annotations.Update;
import org.fzb.common.entity.ProductDO;

/**
 * ProductMapper
 *
 * @author fengzhenbing
 */
public interface ProductMapper {

    /**
     * 减少库存
     * @param productDO
     * @return
     */
    @Update("update product set stock = stock - #{stock}, freezing_stock = freezing_stock + #{stock} " +
            "  where stock - #{stock} >0 and id = #{id}")
    int decreaseStock(ProductDO productDO);

    @Update("update product set freezing_stock = freezing_stock - #{stock} where freezing_stock - #{stock} >0 and id = #{id}")
    int confirm(ProductDO productDO);

    @Update("update product set stock = stock + #{stock}, freezing_stock = freezing_stock - #{stock} " +
            "  where freezing_stock - #{stock} >0 and id = #{id}")
    int cancel(ProductDO productDO);
}
