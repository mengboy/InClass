package com.swjtu.cn.actionmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Classroom;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.service.ClassroomService;

@RestController
public class ManageClassroomAction {
	@Autowired
	ClassroomService classroomService;
	
	@RequestMapping("/manage/classroom/delete")
	public Object delete(HttpServletRequest request,@RequestParam int id){
		Classroom cla = new Classroom();
		cla.setId(id);
		int status = this.classroomService.delete(cla);
		return new Response(status);
	}
	
	
/*	@RequestMapping("/manage/classroom/deletes")
	public Object delete(HttpServletRequest request,@RequestParam Integer[] ids){
		Classroom cla = new Classroom();
		int status = 0;
		try {
			for(int i = 0;i < ids.length;i++){
				int id = ids[i];
				cla.setId(id);
				status = this.classroomService.delete(cla);
				if(status != 0)
					return new Response(status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status);
	}*/
	
	@RequestMapping("/manage/classroom/update")
	public Object update(HttpServletRequest request,@RequestParam(required=false) int id,@RequestParam(required=false) Integer max,@RequestParam(required=false) Integer status,
						 @RequestParam(required=false) Integer building,@RequestParam(required=false) Integer floor){
		Classroom cla = new Classroom();
		cla.setId(id);
		cla.setMax(max);
		cla.setStatus(status);
		cla.setBuilding(building);
		cla.setBuilding(floor);
		int statuss = this.classroomService.update(cla);
		return new Response(statuss);
	}
	
	@RequestMapping("/manage/classroom/add")
	public Object add(HttpServletRequest request,@RequestParam(required=false) Integer id,@RequestParam(required=false) Integer max,
					  @RequestParam(required=false) Integer status,@RequestParam(required=false) Integer building,@RequestParam(required=false) Integer floor){
		Classroom cla = new Classroom();
		cla.setId(id);
		cla.setMax(max);
		cla.setStatus(status);
		cla.setBuilding(building);
		cla.setBuilding(floor);
		int statuss = 0;
		try {
			status = this.classroomService.add(cla);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statuss = Status.ERROR;
		}
		return new Response(statuss);
	}
	
	//拿到所有
	@RequestMapping("/manage/classroom/getbuildings")
	public Object getbuildings(HttpServletRequest request){
		Set<String> set = new HashSet<String>();
		int status = this.classroomService.selectallbuildings(set);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			map = new HashMap<String,Object>();
			map.put("building", it.next());
			list.add(map);
		}
		return new Response(status,list);
	}
	
/*	public static void main(String[] args) {
		String[] ids = new String[]{"1102","1111"};
		for(String id:ids){
			int status = classroomService.
		}
	}*/
}
