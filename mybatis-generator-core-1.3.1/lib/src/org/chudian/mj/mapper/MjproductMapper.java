package org.chudian.mj.mapper;

import org.chudian.mj.bean.Mjproduct;

public interface MjproductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mjproduct record);

    int insertSelective(Mjproduct record);

    Mjproduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mjproduct record);

    int updateByPrimaryKey(Mjproduct record);
}