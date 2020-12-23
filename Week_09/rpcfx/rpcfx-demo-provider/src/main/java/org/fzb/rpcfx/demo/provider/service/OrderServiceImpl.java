package org.fzb.rpcfx.demo.provider.service;

import api.Order;
import api.OrderService;
import org.fzb.rpcfx.annotation.Service;

/**
 * OrderServiceImpl
 *
 * @author fengzhenbing
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findOrderById(Integer id) {
        return null;
    }
}
