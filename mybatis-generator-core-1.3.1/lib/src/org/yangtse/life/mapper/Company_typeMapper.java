package org.yangtse.life.mapper;

import org.yangtse.life.bean.Company_type;

public interface Company_typeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company_type record);

    int insertSelective(Company_type record);

    Company_type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company_type record);

    int updateByPrimaryKey(Company_type record);
}