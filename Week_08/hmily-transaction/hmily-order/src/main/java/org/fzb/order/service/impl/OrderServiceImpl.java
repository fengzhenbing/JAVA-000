package org.fzb.order.service.impl;

import org.dromara.hmily.annotation.HmilyTCC;
import org.fzb.common.entity.MemberDO;
import org.fzb.common.entity.OrderDO;
import org.fzb.common.entity.ProductDO;
import org.fzb.order.feign.AccountClient;
import org.fzb.order.feign.ProductClient;
import org.fzb.order.mapper.OrderMapper;
import org.fzb.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * OrderServiceImpl
 *
 * @author: fengzhenbing
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final AccountClient accountClient;

    private final ProductClient productClient;

    @Autowired(required = false)
    public OrderServiceImpl(final OrderMapper orderMapper, AccountClient accountClient, ProductClient productClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
        this.productClient = productClient;
    }

    @Override
    public BigInteger createOrder() {
        Long memberId = 1L;
        // 下订单
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn("sn_"+Math.random());
        orderDO.setMemberId(memberId);
        orderDO.setStatus(0);
        orderMapper.insert(orderDO);

        // 支付
        pay(orderDO);

        return orderDO.getId();
    }

    @Override
    @HmilyTCC(confirmMethod="confirm",cancelMethod = "cancel")
    // @Transactional(rollbackFor = Exception.class)
    public void pay(OrderDO orderDO) {
        // 待付款
        orderDO.setStatus(0);
        orderMapper.updateStatus(orderDO);
        // 减库存
        ProductDO productDO = new ProductDO();
        productDO.setStock(1);
        productDO.setId(1L);
        productClient.decreaseStock(productDO);

        // 支付
        MemberDO memberDO = new MemberDO();
        memberDO.setId(orderDO.getMemberId());
        memberDO.setBuyMoney(100L);
        accountClient.decreaseBalance(memberDO);
    }

    public void confirm(OrderDO orderDO){
        // 付款完成
        orderDO.setStatus(3);
        orderMapper.updateStatus(orderDO);
    }

    public void cancel(OrderDO orderDO){
        // 无效订单
        orderDO.setStatus(5);
        orderMapper.updateStatus(orderDO);
    }
}
