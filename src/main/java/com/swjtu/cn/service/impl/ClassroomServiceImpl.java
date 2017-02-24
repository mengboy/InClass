package com.swjtu.cn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.cn.bean.Classroom;
import com.swjtu.cn.bean.Classroom_pick;
import com.swjtu.cn.common.QueryBase;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.mapper.ClassroomMapper;
import com.swjtu.cn.mapper.Classroom_pickMapper;
import com.swjtu.cn.service.ClassroomService;
import com.swjtu.cn.utils.Property;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	Log logger = LogFactory.getLog(ClassroomServiceImpl.class);
	@Autowired
	ClassroomMapper classroomMapper;
	@Autowired
	Classroom_pickMapper classroom_pickMapper;
	
	@Override
	public int add(Classroom obj) {
		// TODO Auto-generated method stub
		if(this.classroomMapper.insertSelective(obj) > 0){
			logger.info("添加classroom成功,id:"+obj.getId());
			return Status.SUCCESS;
		}else{
			logger.warn("添加classroom失败,id:"+obj.getId()==null?"空":obj.getId());
			return Status.ERROR;
		}
	}

	@Override
	public int update(Classroom obj) {
		// TODO Auto-generated method stub
		if(this.classroomMapper.updateByPrimaryKeySelective(obj) > 0){
			logger.info("classroom更新成功,id:"+obj.getId());
			return Status.SUCCESS;
		}else{
			logger.warn("classroom更新失败,id:"+obj.getId()==null?"空":obj.getId());
			return Status.ERROR;
		}
	}

	@Override
	public int delete(Classroom obj) {
		// TODO Auto-generated method stub
		if(this.classroomMapper.deleteByPrimaryKey(obj.getId()) > 0){
			logger.info("classroom删除成功,id:"+obj.getId());
			return Status.SUCCESS;
		}else{
			logger.warn("classroom删除失败,id:"+obj.getId()==null?"空":obj.getId());
			return Status.ERROR;
		}
	}

	@Override
	public Classroom get(int id) {
		// TODO Auto-generated method stub
		return this.classroomMapper.selectByPrimaryKey(id);
	}

	@Override
	public Set<String> getrecommends() {
		// TODO Auto-generated method stub
		logger.info(Property.getproperty("recommend"));
		List<Classroom_pick> list = this.classroom_pickMapper.getrecommends(Property.getproperty("recommend"));
		Set<String> set = new HashSet<String>();
		if(list.size() == 0)
			logger.info("没有可推荐的教室");
		for(Classroom_pick cla:list){
			set.add(cla.getClassroomId().toString().substring(0,1));
		}
		return set;
	}

	@Override
	public int getrecommendbybuilding(String building, List<Map<String,Object>> return_list) {
		// TODO Auto-generated method stub
		List<Classroom_pick> list = new ArrayList<Classroom_pick>();
		try {
			list = this.classroom_pickMapper.getrecommendbybuilding(Property.getproperty("recommend"),building);
			Map<String,Object> map = null;
			for(int i = 0;i < list.size();i++){
				Classroom_pick cla_pick = list.get(i);
				Classroom cla = this.classroomMapper.selectByPrimaryKey(cla_pick.getClassroomId());
				map = new HashMap<String,Object>();
				map.put("id", cla.getId());
				map.put("rate", cla_pick.getNum()*100/cla.getMax());//要加上%
				map.put("pick_time", cla_pick.getPickTime());
				map.put("order", i+1);
				return_list.add(map);
			}
			return Status.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("查找"+building+"教的推荐教室失败");
			return Status.ERROR;
		}
	}

	@Override
	public int selectall(QueryBase querybase) {
		// TODO Auto-generated method stub
		try {
			querybase.setTotalRow(this.classroomMapper.selectallcount(querybase));
			List<Classroom> list = this.classroomMapper.selectall(querybase);
			List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for(Classroom cla:list){
				Classroom_pick cla_pick = this.classroom_pickMapper.selectByclaid(cla.getId());
				map = new HashMap<String,Object>();
				map.put("id", cla.getId());
				if(cla_pick == null){
					map.put("rate", "暂无记录");//占用比
					map.put("time", "暂无记录");
				}
				else{
					map.put("rate", cla_pick.getNum()*100/cla.getMax());//占用比
					map.put("time", cla_pick.getPickTime());
				}
				map.put("max", cla.getMax());
				map.put("num", cla_pick.getNum());
				map.put("status", cla.getStatus());
				list_map.add(map);
			}
			querybase.setResults(list_map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("查询所有教室状态失败");
			return Status.ERROR;
		}
		return Status.SUCCESS;
	}

	//按条件查找
	@Override
	public int selectBY(QueryBase queryBase) {
		// TODO Auto-generated method stub
		try{
			queryBase.setTotalRow(this.classroomMapper.selectBycount(queryBase));
			List<Classroom> list_classroom = this.classroomMapper.selectBy(queryBase);
			Classroom_pick cla_pick = null;
			List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>(); 
			for(Classroom cla : list_classroom){
				Map<String,Object> map = new HashMap<String,Object>(); 
				cla_pick = this.classroom_pickMapper.selectByclaid(cla.getId());
				map.put("id", cla.getId());
				map.put("time", cla_pick.getPickTime());
				map.put("rate", cla_pick.getNum()*100/cla.getMax());
				list_map.add(map);
			}
			queryBase.setResults(list_map);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("查询教室状态失败");
			return Status.ERROR;
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Classroom> getfreeroom() {
		// TODO Auto-generated method stub
		List<Classroom> list = this.classroomMapper.getfreeroom();
		return list;
	}

	@Override
	public int selectallbuildings(Set<String> set) {
		// TODO Auto-generated method stub
		int status = Status.SUCCESS;
		try {
			List<Classroom> list = this.classroomMapper.selectallnopage();
			for(Classroom cla:list){
				set.add(cla.getBuilding()+"");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.SUCCESS;
		}
		return status;
	}
	

}
