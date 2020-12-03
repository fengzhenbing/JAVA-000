package org.fzb.multDatasource.service.impl;

import org.fzb.multDatasource.entity.OrderDO;
import org.fzb.multDatasource.mapper.OrderMapper;
import org.fzb.multDatasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 *
 * @author: fengzhenbing
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public int createOrder() {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn("sn_"+Math.random());
       // orderDO.setMemberId(999L);
        orderDO.setDeleteStatus(0);
        return orderMapper.insert(orderDO);
    }

    @Override
    public OrderDO findOrderById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
