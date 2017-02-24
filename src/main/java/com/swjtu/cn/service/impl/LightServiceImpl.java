package com.swjtu.cn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.cn.bean.Apply;
import com.swjtu.cn.bean.Light;
import com.swjtu.cn.common.QueryBase;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.mapper.ApplyMapper;
import com.swjtu.cn.mapper.LightMapper;
import com.swjtu.cn.service.LightService;

@Service
public class LightServiceImpl implements LightService {
	Log logger = LogFactory.getLog(LightServiceImpl.class);
	@Autowired
	ApplyMapper applyMapper;
	@Autowired
	LightMapper lightMapper;
	
	@Override
	public int add(Light obj) {
		// TODO Auto-generated method stub
		if(this.lightMapper.insertSelective(obj) > 0)
			return Status.SUCCESS;
		else
			return Status.ERROR;
	}

	@Override
	public int update(Light obj) {
		// TODO Auto-generated method stub
		if(this.lightMapper.updateByPrimaryKeySelective(obj) > 0){
			logger.info("更新成功");
			return Status.SUCCESS;
		}
		else
			return Status.ERROR;
	}

	@Override
	public int delete(Light obj) {
		// TODO Auto-generated method stub
		if(this.lightMapper.deleteByPrimaryKey(obj.getId()) > 0)
			return Status.SUCCESS;
		else
			return Status.ERROR;
	}

	@Override
	public Light get(int id) {
		// TODO Auto-generated method stub
		return this.lightMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Map<String,Object>> selectcontrolbystu(int number) {
		// TODO Auto-generated method stub
		List<Apply> list =this.applyMapper.getcontrolclassroom(number);
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		List<Light> list_light = null;
		for(Apply apply :list){
			map = new HashMap<String,Object>();
			int id = apply.getClassroomId();
			list_light = this.lightMapper.selectcontrolbystu(id + "_");
			
			map.put("name", id);
			map.put("lights", list_light);
			list_map.add(map);
		}
		return list_map;
	}

	//管理员查找
	@Override
	public int selectByAdmin(QueryBase querybase) {
		// TODO Auto-generated method stub
		int status = 0;
/*		try {
			querybase.setTotalRow(this.lightMapper.selectcountbyadmin(querybase));
			querybase.setResults(this.lightMapper.selectbyadmin(querybase));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}*/
		try {
			querybase.setTotalRow(this.lightMapper.selectcountbyadmin(querybase));
			List<Light> list = this.lightMapper.selectbyadmin(querybase);
			List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			List<Light> list_eachmap = new ArrayList<Light>();
			String classroom_id = "";
			if(list.size() != 0){
				Integer id = list.get(0).getId();
				classroom_id = getclassroom_id(id);
				list_eachmap.add(list.get(0));
			}
			for(int i = 1;i < list.size();i++){
				Integer id = list.get(i).getId();
				if(getclassroom_id(id).equals(classroom_id)){
					list_eachmap.add(list.get(i));
				}else{
					map.put("classname", classroom_id);
					map.put("lights", list_eachmap);
					list_map.add(map);
					map = new HashMap<String,Object>(); 
					list_eachmap = new ArrayList<Light>();
					classroom_id = getclassroom_id(id);
					list_eachmap.add(list.get(i));
				}
			}
			map.put("classname", classroom_id);
			map.put("lights", list_eachmap);
			list_map.add(map);
			querybase.setResults(list_map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return status;
	}

	public String getclassroom_id(Integer id){
		return id.toString().substring(0, 4);
	}

	//更新控制模式
	@Override
	public int updatemode(String classroom_id, int mode) {
		// TODO Auto-generated method stub
		int status = this.lightMapper.updatemode(classroom_id+"_", mode);
		if(status > 0)
			status = Status.SUCCESS;
		else
			status = Status.ERROR;
		return status;
	}

	@Override
	public int updatestatus(int id, int status) {
		// TODO Auto-generated method stub
		System.out.println("id:"+id+",status:"+status);
		if(this.lightMapper.updatestatus(id, status) > 0)
			return Status.SUCCESS;
		else
			return Status.ERROR;
	}

	@Override
	public List<Light> getstatuschange() {
		// TODO Auto-generated method stub
		return this.lightMapper.getchanges(1);
		/*return null;*/
	}

	@Override
	public List<Light> getmodechange() {
		// TODO Auto-generated method stub
		return this.lightMapper.getchanges(2);
	}

	//查找某个教室的所有终端,模糊查询
	@Override
	public List<Light> getlightstatuschange(String likeid) {
		// TODO Auto-generated method stub
		return this.lightMapper.getlightstatuschange(likeid);
	}

	//远程扫描完模式后更新模式
	@Override
	public int updatemodebyremote(Light obj,String like_id) {
		// TODO Auto-generated method stub
		if(this.lightMapper.updatemodebyremote(obj,like_id) > 0){
			logger.info("更新成功");
			return Status.SUCCESS;
		}
		else
			return Status.ERROR;
	}

}
