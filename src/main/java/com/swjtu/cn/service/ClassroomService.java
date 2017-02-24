package com.swjtu.cn.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.swjtu.cn.bean.Classroom;
import com.swjtu.cn.common.QueryBase;

public interface ClassroomService extends BaseService<Classroom> {
	
	//获得有推荐教室信息的楼号
	Set<String> getrecommends();
	
	//获得相应楼的推荐教室
	int getrecommendbybuilding(String building,List<Map<String,Object>> list);
	
	//查找所有
	int selectall(QueryBase querybase);
	
	//按条件查找
	int selectBY(QueryBase queryBase);

	List<Classroom> getfreeroom();
	
	//查找所有的教学楼号
	int selectallbuildings(Set<String> set);
	
}
