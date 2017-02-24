package org.chudian.mj.mapper;

import org.chudian.mj.bean.ThreeD;

public interface ThreeDMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThreeD record);

    int insertSelective(ThreeD record);

    ThreeD selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThreeD record);

    int updateByPrimaryKey(ThreeD record);
}