package org.yangtse.life.mapper;

import org.yangtse.life.bean.CompanyUser;
import org.yangtse.life.bean.CompanyUserKey;

public interface CompanyUserMapper {
    int deleteByPrimaryKey(CompanyUserKey key);

    int insert(CompanyUser record);

    int insertSelective(CompanyUser record);

    CompanyUser selectByPrimaryKey(CompanyUserKey key);

    int updateByPrimaryKeySelective(CompanyUser record);

    int updateByPrimaryKey(CompanyUser record);
}