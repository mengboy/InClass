package org.yangtse.life.mapper;

import org.yangtse.life.bean.Crowdfunding_orderKey;

public interface Crowdfunding_orderMapper {
    int deleteByPrimaryKey(Crowdfunding_orderKey key);

    int insert(Crowdfunding_orderKey record);

    int insertSelective(Crowdfunding_orderKey record);
}