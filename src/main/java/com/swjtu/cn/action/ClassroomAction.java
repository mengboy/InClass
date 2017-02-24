package com.swjtu.cn.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Classroom;
import com.swjtu.cn.common.QueryBase;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.service.ClassroomService;
import com.swjtu.cn.utils.Property;



@RestController
public class ClassroomAction {
	Log logger = LogFactory.getLog(ClassroomAction.class);
	@Autowired
	ClassroomService classroomService;
	//String[] ids = new String[]{"1102","1111"};
	
	//所有有可推荐教室的教学楼
	@RequestMapping("/inclass/api/classroom/getbuildings")
	public Object getbulidings(HttpServletRequest request){
		Set<String> set = this.classroomService.getrecommends();
		Map<String,Object> return_map = new HashMap<String,Object>();
		Iterator<String> it = set.iterator();
		Map<String,Object> map = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			map = new HashMap<String,Object>();
			map.put("building", it.next());
			list.add(map);
		}
		return_map.put("buildings", list);
		return new Response(Status.SUCCESS,return_map);
	}
	
	//获得相应教学楼的推荐教室
	@RequestMapping("/inclass/api/classroom/getrecommendbybuilding")
	public Object getrecommendbybuilding(HttpServletRequest request,@RequestParam String building){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int status = this.classroomService.getrecommendbybuilding(building, list);
		return new Response(status,list);
	}
	
	//定义推荐教室阈值
	@RequestMapping("/inclass/api/classroom/setrecommentlimit")
	public Object setrecommentlimit(HttpServletRequest request,@RequestParam String recommend){
		int status;
		try {
			Property.setproperty("recommend", recommend);
			status = Status.SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status);
	}
	
	//查找所有教室信息,type
	@RequestMapping("/inclass/api/classroom/selectall")
	public Object selectall(HttpServletRequest request,@RequestParam(required=false) String building,@RequestParam long requestPage,@RequestParam long pageSize){
		QueryBase querybase = new QueryBase();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("building", building);
		querybase.setPageSize(pageSize);
		querybase.setCurrentPage(requestPage);
		querybase.setParameters(parameters);
		int status = this.classroomService.selectall(querybase);
		return new Response(status,querybase);
	}
	
	//按教学楼、编号查找，此处在页面上更改下，改成按条件查找(教学楼、编号，下拉框),1代表按照教学楼和层号，2按照编号,3代表查找所有
	@RequestMapping("/inclass/api/classroom/selectby")
	public Object selectBY(HttpServletRequest request,@RequestParam int type,@RequestParam(required=false) String building,@RequestParam(required=false) String floor,@RequestParam(required=false) String id,
						   @RequestParam long requestPage,@RequestParam long pageSize){
		QueryBase queryBase = new QueryBase();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("type", type);
		parameters.put("buildingandfloor", building+floor+"%");
		parameters.put("id", id);
		queryBase.setPageSize(pageSize);
		queryBase.setCurrentPage(requestPage);
		queryBase.setParameters(parameters);
		int status = this.classroomService.selectBY(queryBase);
		return new Response(status,queryBase);
	}
	
	//批量删除
	@RequestMapping("/inclass/api/classroom/deletes")
	public Object deletes(@RequestParam String[] ids){
		int status = 0;
		Classroom classroom = null;
		System.out.println("lengths:"+ids.length);
		try {
			for(String id:ids){
				System.out.println(id);
				classroom = new Classroom();
				classroom.setId(Integer.parseInt(id));
				status = this.classroomService.delete(classroom);
				if(status != 0)
					return new Response(status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status = Status.ERROR;
		}
		return new Response(status);
	}
	
/*	//批量删除
	@RequestMapping("/api/classroom/deletes")
	public Object deletes(){
		int status = 0;
		Classroom classroom = null;
		System.out.println("lengths:"+ids.length);
		try {
			for(String id:ids){
				classroom = new Classroom();
				classroom.setId(Integer.parseInt(id));
				status = this.classroomService.delete(classroom);
				if(status != 0)
					return new Response(status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			logger.info("");
			status = Status.ERROR;
		}
		return new Response(status);
	}*/
	
	//查找未开放教室
	@RequestMapping("/inclass/api/classroom/getfreeroom")
	public Object getfreeroom(){
		List<Classroom> list = this.classroomService.getfreeroom();
		if(list.size() == 0)
			return new Response(Status.NOT_EXISTS);
		return new Response(0,list);
	}
	
	//新开放教室
	@RequestMapping("/inclass/api/classroom/addopenroom")
	public Object addopenroom(@RequestParam String[] ids){
		Classroom classroom = null;
		int status = 0;
		try {
			for(int i = 0;i < ids.length;i++){
				classroom = new Classroom();
				classroom.setId(Integer.parseInt(ids[i]));
				classroom.setStatus(1);//设置为已开放
				status = this.classroomService.update(classroom);
				if(status != 0)
					return new Response(status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status);
	}
	
}
