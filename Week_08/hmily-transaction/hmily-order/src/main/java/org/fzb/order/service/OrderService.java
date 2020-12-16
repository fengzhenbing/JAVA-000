package org.fzb.order.service;

import org.fzb.common.entity.OrderDO;

import java.math.BigInteger;

/**
 * 订单服务
 *
 * @author: fengzhenbing
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @return
     */
    BigInteger createOrder();

    void pay(OrderDO orderDO);
}
