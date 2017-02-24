package com.swjtu.cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swjtu.cn.bean.Light;
import com.swjtu.cn.common.QueryBase;

public interface LightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Light record);

    int insertSelective(Light record);

    Light selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Light record);

    int updateByPrimaryKey(Light record);
    
    //查找学生控制的灯
    List<Light> selectcontrolbystu(String likeclassroom_id);
    
    //管理员查找
    List<Light> selectbyadmin(QueryBase querybase);
    
    //管理员查找的数量
    long selectcountbyadmin(QueryBase querybase);
    
    //更新控制模式
    int updatemode(String likeid,int mode);
    
    //远程控制状态
    int updatestatus(int id,int status);
    
    //获得控制模式或状态状态更改的终端
    List<Light> getchanges(@Param("type") int type);
    
    //获得控制模式或状态更改的终端
    List<Light> getlightstatuschange(String likeid);
    
    //远程扫描完模式后更新模式
    int updatemodebyremote(@Param("light")Light record,@Param("like_id") String like_id);
    
}