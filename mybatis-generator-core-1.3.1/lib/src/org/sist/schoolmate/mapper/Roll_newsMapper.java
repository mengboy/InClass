package org.sist.schoolmate.mapper;

import org.sist.schoolmate.bean.Roll_news;

public interface Roll_newsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roll_news record);

    int insertSelective(Roll_news record);

    Roll_news selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roll_news record);

    int updateByPrimaryKey(Roll_news record);
}