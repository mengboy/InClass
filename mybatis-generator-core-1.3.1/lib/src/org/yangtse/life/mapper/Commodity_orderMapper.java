package org.yangtse.life.mapper;

import org.yangtse.life.bean.Commodity_order;
import org.yangtse.life.bean.Commodity_orderKey;

public interface Commodity_orderMapper {
    int deleteByPrimaryKey(Commodity_orderKey key);

    int insert(Commodity_order record);

    int insertSelective(Commodity_order record);

    Commodity_order selectByPrimaryKey(Commodity_orderKey key);

    int updateByPrimaryKeySelective(Commodity_order record);

    int updateByPrimaryKey(Commodity_order record);
}