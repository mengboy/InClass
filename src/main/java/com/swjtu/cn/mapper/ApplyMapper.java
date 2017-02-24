package com.swjtu.cn.mapper;

import java.util.List;

import com.swjtu.cn.bean.Apply;
import com.swjtu.cn.common.QueryBase;

public interface ApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
    
    List<Apply> selectBystu(int number);
    
    List<Apply> selectByAdmin(QueryBase querybase);
    
    long selectcountByAdmin(QueryBase querybase);
    
    //查找学生可控制的教室
    List<Apply> getcontrolclassroom(int number);
}