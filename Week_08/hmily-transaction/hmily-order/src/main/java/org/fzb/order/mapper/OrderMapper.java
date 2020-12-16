package org.fzb.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.fzb.common.entity.OrderDO;

/**
 * OrderMapper
 *
 * @author fengzhenbing
 */
public interface OrderMapper {
    @Insert(" insert into `order` (create_time,order_sn,member_id ) " +
            " values ( now(),#{orderSn},#{memberId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(OrderDO orderDO);

    @Update("update `order` set status = #{status} where id = #{id} ")
    int updateStatus(OrderDO orderDO);
}
