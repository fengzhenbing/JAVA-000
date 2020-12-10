package org.fzb.ssproxySharding.service;


import org.fzb.ssproxySharding.entity.OrderDO;

import java.math.BigInteger;

/**
 * 订单服务
 *
 * @author fengzhenbing
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @return
     */
    BigInteger createOrder();

    OrderDO findOrderById(Long id);

    void deleteOrderById(Long id);

    OrderDO updateOrderById(Long id);
}
