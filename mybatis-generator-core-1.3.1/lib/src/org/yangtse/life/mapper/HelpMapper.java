package org.yangtse.life.mapper;

import org.yangtse.life.bean.Help;

public interface HelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Help record);

    int insertSelective(Help record);

    Help selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Help record);

    int updateByPrimaryKey(Help record);
}