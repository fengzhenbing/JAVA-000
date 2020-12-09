package org.fzb.ssproxySharding.mapper;


import org.fzb.ssproxySharding.entity.OrderDO;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}