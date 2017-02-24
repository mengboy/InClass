package org.yangtse.life.mapper;

import org.yangtse.life.bean.PraiseUserKey;

public interface PraiseUserMapper {
    int deleteByPrimaryKey(PraiseUserKey key);

    int insert(PraiseUserKey record);

    int insertSelective(PraiseUserKey record);
}