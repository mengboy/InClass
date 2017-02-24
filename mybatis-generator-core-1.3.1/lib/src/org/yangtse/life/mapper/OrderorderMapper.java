package org.yangtse.life.mapper;

import org.yangtse.life.bean.Orderorder;

public interface OrderorderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderorder record);

    int insertSelective(Orderorder record);

    Orderorder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderorder record);

    int updateByPrimaryKey(Orderorder record);
}