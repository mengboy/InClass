package org.yangtse.life.mapper;

import org.yangtse.life.bean.FollowKey;

public interface FollowMapper {
    int deleteByPrimaryKey(FollowKey key);

    int insert(FollowKey record);

    int insertSelective(FollowKey record);
}