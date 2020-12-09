package org.fzb.ssproxySharding.controller;

import org.fzb.ssproxySharding.entity.OrderDO;
import org.fzb.ssproxySharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * OrderController
 *
 * @author: fzb
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public OrderDO findOrder(Long id){
        return  orderService.findOrderById(id);
    }

    @GetMapping("/create")
    public BigInteger createOrder(){
        return  orderService.createOrder();
    }

    @GetMapping("/delete")
    public String deleteOrder(Long id){
        orderService.deleteOrderById(id);
        return "success";
    }

    @GetMapping("/update")
    public OrderDO updateOrder(Long id){
        return  orderService.updateOrderById(id);
    }

}