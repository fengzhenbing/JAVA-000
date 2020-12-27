package org.fzb.rpcfx.demo.consumer.controller;

import org.fzb.rpcfx.demo.api.Order;
import org.fzb.rpcfx.demo.api.OrderService;
import org.fzb.rpcfx.annotation.Referenced;
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

    @Referenced
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order findOrder(@PathVariable("id") Integer id){
        return orderService.findOrderById(id);
    }
}
