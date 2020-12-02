package org.fzb.multDatasource.controller;

import org.fzb.multDatasource.entity.OrderDO;
import org.fzb.multDatasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 *
 * @author: fzb
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public OrderDO findOrder(Long id){
        return  orderService.findOrderById(id);
    }

    @GetMapping("/create")
    public int createOrder(){
        return  orderService.createOrder();
    }
}