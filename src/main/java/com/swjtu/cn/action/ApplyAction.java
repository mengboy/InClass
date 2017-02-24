package com.swjtu.cn.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Apply;
import com.swjtu.cn.bean.Student;
import com.swjtu.cn.common.QueryBase;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.service.ApplyService;
import com.swjtu.cn.utils.ActionUtil;

@RestController
public class ApplyAction {
	@Autowired
	ApplyService applyService;
	
	@RequestMapping("/inclass/api/apply/add")
	public Object add(HttpServletRequest request,@RequestParam String name,@RequestParam String number,@RequestParam String classname,
					  @RequestParam String cardnumber,@RequestParam String starttime,@RequestParam String endtime,@RequestParam String reason/*@RequestParam(required=false) MultipartFile file*/) throws Exception{
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int status = 0;
		if(name.equals(new String(name.getBytes("iso8859-1"), "iso8859-1")))
		{
			name=new String(name.getBytes("iso8859-1"),"utf-8");
			System.out.println("name1:"+name);
		}
/*		if(name.equals(new String(name.getBytes("utf-8"), "utf-8")))
		{
			name=new String(name.getBytes("utf-8"),"utf-8");
			System.out.println("name2:"+name);
		}
		if(name.equals(new String(name.getBytes("GB2312"), "GB2312")))
		{
			name=new String(name.getBytes("GB2312"),"utf-8");
			System.out.println("name3:"+name);
		}
		if(name.equals(new String(name.getBytes("iso8859_1"), "iso8859_1")))
		{
			name=new String(name.getBytes("iso8859_1"),"utf-8");
			System.out.println("name4:"+name);
		}
		if(name.equals(new String(name.getBytes("GBK"), "GBK")))
		{
			name=new String(name.getBytes("GBK"),"utf-8");
			System.out.println("name5:"+name);
		}*/
		if(classname.equals(new String(classname.getBytes("iso8859-1"), "iso8859-1")))
		{
			classname=new String(classname.getBytes("iso8859-1"),"utf-8");
			System.out.println("classname:"+classname);
		}
		if(reason.equals(new String(reason.getBytes("iso8859-1"), "iso8859-1")))
		{
			reason=new String(reason.getBytes("iso8859-1"),"utf-8");
			System.out.println("reason:"+reason);
		}
		Apply apply = new Apply();
		try {
			apply.setStartTime(f.parse(starttime));
			apply.setEndTime(f.parse(endtime));
			apply.setApplyTime(new Date());
			apply.setContent(reason);
			apply.setStudentClass(classname);
			apply.setStudentNumber(Integer.valueOf(number));
			apply.setStudentName(name);
			apply.setStudentCard(cardnumber);
			String url = "";
//			System.out.println("空:"+file == null);
//			if(file != null)
//				url = FileUtil.downloadFile(request, file,"images");
//			if(url == null)
//				status = Status.ERROR;
//			else{
//				apply.setAppendixAddr(url);
//				status = this.applyService.add(apply);
//			}
			apply.setAppendixAddr(url);
			status = this.applyService.add(apply);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status);
	}
	
	@RequestMapping("/inclass/api/apply/selectBystu")
	public Object selectBystu(HttpServletRequest request){
		Student stu = (Student) ActionUtil.getCurrentUser(request);
		List<Apply> list = null;
		int status = 0;
		System.out.println(stu.getStudentNumber());
		try {
			list = this.applyService.selectBystu(stu.getStudentNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.SUCCESS;
		}
		return new Response(status,list);
	}
	
	@RequestMapping("/inclass/api/apply/selectbyadmin")
	public Object selectbyadmin(HttpServletRequest request,@RequestParam int check_status,@RequestParam long requestPage,@RequestParam long pageSize){
		QueryBase querybase = new QueryBase();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("check_status", check_status);
		querybase.setParameters(parameters);
		querybase.setPageSize(pageSize);
		querybase.setCurrentPage(requestPage);
		int status = this.applyService.selectByAdmin(querybase);
		return new Response(status,querybase);
	}
	
	@RequestMapping("/inclass/api/apply/get")
	public Object get(HttpServletRequest request,@RequestParam int id){
		Apply apply = this.applyService.get(id);
		int status = 0;
		if(apply == null){
			status = Status.ERROR;
		}
		return new Response(status,apply);
	}
	
	//删除
	@RequestMapping("/inclass/api/apply/delete")
	public Object deletes(HttpServletRequest request,@RequestParam int id){
		int status = 0;
		Apply apply = new Apply();
		apply.setId(id);
		status = this.applyService.delete(apply);
		return new Response(status);
		
	}

}
