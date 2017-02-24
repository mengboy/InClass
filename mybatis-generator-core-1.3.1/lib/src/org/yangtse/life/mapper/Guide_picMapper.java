package org.yangtse.life.mapper;

import org.yangtse.life.bean.Guide_pic;

public interface Guide_picMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Guide_pic record);

    int insertSelective(Guide_pic record);

    Guide_pic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Guide_pic record);

    int updateByPrimaryKey(Guide_pic record);
}