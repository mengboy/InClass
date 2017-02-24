package com.swjtu.cn.mapper;

import java.util.List;

import com.swjtu.cn.bean.Classroom;
import com.swjtu.cn.common.QueryBase;

public interface ClassroomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);
  
    List<Classroom> selectall(QueryBase querybase);
    
    long selectallcount(QueryBase querybase);
    
    List<Classroom> selectBy(QueryBase queryBase);
    
    long selectBycount(QueryBase queryBase);

	List<Classroom> getfreeroom();
	
	List<Classroom> selectallnopage();
}