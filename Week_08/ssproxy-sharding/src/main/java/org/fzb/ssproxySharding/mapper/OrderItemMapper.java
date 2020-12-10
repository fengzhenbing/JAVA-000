package org.fzb.ssproxySharding.mapper;

import org.fzb.ssproxySharding.entity.OrderItemDO;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByOrderId(Long id);

    int insert(OrderItemDO record);

    int insertSelective(OrderItemDO record);

    OrderItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItemDO record);

    int updateByPrimaryKey(OrderItemDO record);
}