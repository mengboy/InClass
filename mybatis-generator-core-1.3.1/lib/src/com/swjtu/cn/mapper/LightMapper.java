package com.swjtu.cn.mapper;

import com.swjtu.cn.bean.Light;

public interface LightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Light record);

    int insertSelective(Light record);

    Light selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Light record);

    int updateByPrimaryKey(Light record);
}