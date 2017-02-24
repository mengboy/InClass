package com.swjtu.cn.mapper;

import com.swjtu.cn.bean.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentNumber);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentNumber);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    Student login(int stu_number,String password);
}