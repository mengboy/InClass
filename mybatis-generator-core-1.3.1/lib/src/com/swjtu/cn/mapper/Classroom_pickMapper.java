package com.swjtu.cn.mapper;

import com.swjtu.cn.bean.Classroom_pick;

public interface Classroom_pickMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classroom_pick record);

    int insertSelective(Classroom_pick record);

    Classroom_pick selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classroom_pick record);

    int updateByPrimaryKey(Classroom_pick record);
}