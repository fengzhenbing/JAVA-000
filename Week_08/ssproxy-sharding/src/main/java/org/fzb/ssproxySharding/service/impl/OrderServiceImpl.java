package org.fzb.ssproxySharding.service.impl;

import org.fzb.ssproxySharding.entity.OrderDO;
import org.fzb.ssproxySharding.entity.OrderItemDO;
import org.fzb.ssproxySharding.mapper.OrderItemMapper;
import org.fzb.ssproxySharding.mapper.OrderMapper;
import org.fzb.ssproxySharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * OrderServiceImpl
 *
 * @author fengzhenbing
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigInteger createOrder() {
        long memberId = (long) (Math.random()*10000000);
        long orderSn = (long) (Math.random()*1000000000);
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn(orderSn+"");
        orderDO.setMemberId(memberId);
        orderDO.setDeleteStatus(0);
        orderMapper.insert(orderDO);

        OrderItemDO orderItem = new OrderItemDO();
        orderItem.setMemberId(memberId);
        orderItem.setOrderId(orderDO.getId());
        orderItem.setOrderSn(orderSn+"");
        orderItem.setProductId((long) (Math.random()*1000));
        orderItemMapper.insert(orderItem);

        return orderDO.getId();
    }

    @Override
    public OrderDO findOrderById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderItemMapper.deleteByOrderId(id);
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderDO updateOrderById(Long id) {
        OrderDO order = orderMapper.selectByPrimaryKey(id);
        order.setNote("note"+Math.random()*10000000);
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }
}
