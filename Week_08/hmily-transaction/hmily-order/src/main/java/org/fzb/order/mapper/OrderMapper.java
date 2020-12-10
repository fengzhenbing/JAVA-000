package org.fzb.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.fzb.order.entity.OrderDO;

/**
 * OrderMapper
 *
 * @author fengzhenbing
 */
public interface OrderMapper {
    @Insert(" insert into `order` (create_time,order_sn,status,member_id ) " +
            " values ( now(),#{orderSn},#{status},#{memberId})")
    int insert(OrderDO orderDO);
}
