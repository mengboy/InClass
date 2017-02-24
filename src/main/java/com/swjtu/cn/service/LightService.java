package com.swjtu.cn.service;

import java.util.List;
import java.util.Map;

import com.swjtu.cn.bean.Light;
import com.swjtu.cn.common.QueryBase;

public interface LightService extends BaseService<Light> {
	
	//查找学生控制的终端
	List<Map<String,Object>> selectcontrolbystu(int number);
	
	//管理员查找
	int selectByAdmin(QueryBase querybase);
	
	//更新终端控制模式
	int updatemode(String classroom_id,int mode);
	
	//更新终端状态
	int updatestatus(int id,int status);
	
	//找出状态人为改变的终端
	List<Light> getstatuschange();
	
	//找出控制模式人为改变的终端
	List<Light> getmodechange();
	
	//查找某个
	List<Light> getlightstatuschange(String likeid);
	
	//远程扫描到mode后更新该id
	int updatemodebyremote(Light obj,String like_id);
	
}
