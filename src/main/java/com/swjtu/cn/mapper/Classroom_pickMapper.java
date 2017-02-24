package com.swjtu.cn.mapper;

import java.util.List;

import com.swjtu.cn.bean.Classroom_pick;

public interface Classroom_pickMapper {
    int deleteByPrimaryKey(Integer classroomId);

    int insert(Classroom_pick record);

    int insertSelective(Classroom_pick record);

    Classroom_pick selectByPrimaryKey(Integer classroomId);

    int updateByPrimaryKeySelective(Classroom_pick record);

    int updateByPrimaryKey(Classroom_pick record);
    
    List<Classroom_pick> getrecommends(String recommend);
    
    //传入匹配楼号字符串
    List<Classroom_pick> getrecommendbybuilding(String recommend, String fixedstr);
    
    //按id查找教室的最新状态
    Classroom_pick selectByclaid(int cla_id);
    
}