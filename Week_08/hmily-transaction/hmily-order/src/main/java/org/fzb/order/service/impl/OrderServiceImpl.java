package org.fzb.order.service.impl;

import org.fzb.order.entity.OrderDO;
import org.fzb.order.mapper.OrderMapper;
import org.fzb.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 *
 * @author: fengzhenbing
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Autowired(required = false)
    public OrderServiceImpl(final OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public int createOrder() {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn("sn_"+Math.random());
        orderDO.setMemberId(999L);
        orderDO.setStatus(0);
        return orderMapper.insert(orderDO);
    }
}
