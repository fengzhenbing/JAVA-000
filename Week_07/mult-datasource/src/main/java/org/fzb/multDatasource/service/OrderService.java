package org.fzb.multDatasource.service;

import org.fzb.multDatasource.entity.OrderDO;

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
    int createOrder();

    OrderDO findOrderById(Long id);
}
