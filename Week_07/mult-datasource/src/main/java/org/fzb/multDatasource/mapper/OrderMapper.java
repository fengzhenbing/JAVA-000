package org.fzb.multDatasource.mapper;

import org.fzb.multDatasource.config.Const;
import org.fzb.multDatasource.config.Datasourcekey;
import org.fzb.multDatasource.entity.OrderDO;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    @Datasourcekey(Const.DATASOURCE_SLAVE)
    OrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}