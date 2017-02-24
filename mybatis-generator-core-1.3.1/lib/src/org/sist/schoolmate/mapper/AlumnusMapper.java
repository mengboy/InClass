package org.sist.schoolmate.mapper;

import org.sist.schoolmate.bean.Alumnus;

public interface AlumnusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Alumnus record);

    int insertSelective(Alumnus record);

    Alumnus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alumnus record);

    int updateByPrimaryKey(Alumnus record);
}