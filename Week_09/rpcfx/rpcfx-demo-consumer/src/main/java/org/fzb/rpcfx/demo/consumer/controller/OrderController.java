package org.fzb.rpcfx.demo.consumer.controller;

import api.Order;
import api.OrderService;
import api.User;
import org.fzb.rpcfx.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 *
 * @author fengzhenbing
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order findOrder(@PathVariable("id") Integer id){
        return orderService.findOrderById(id);
    }
}
