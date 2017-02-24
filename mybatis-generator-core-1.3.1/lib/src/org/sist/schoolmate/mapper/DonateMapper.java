package org.sist.schoolmate.mapper;

import org.sist.schoolmate.bean.Donate;

public interface DonateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Donate record);

    int insertSelective(Donate record);

    Donate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Donate record);

    int updateByPrimaryKey(Donate record);
}