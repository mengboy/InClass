package com.swjtu.cn.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Student;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.service.StudentService;
import com.swjtu.cn.utils.ActionUtil;

@RestController
public class StudentAction {
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/inclass/api/student/login")
	public Object login(HttpServletRequest request,@RequestParam String password,@RequestParam int number){
		Student stu = this.studentService.login(number, password);
		if(stu != null){
//			if((stu.getIslogin() != null) && (stu.getIslogin() == 1))//表明已经登录了
//				return new Response(Status.EXISTS);
//			stu.setIslogin(1);
//			if(this.studentService.update(stu)!=0)
//				return new Response(Status.ERROR);
			ActionUtil.setCurrentUser(request, stu);
			return new Response(Status.SUCCESS);
		}else{
			return new Response(Status.ERROR);
		}
	}
	
	@RequestMapping("/inclass/api/student/loginout")
	public Object loginout(HttpServletRequest request){
		try {
			Student stu = (Student)ActionUtil.getCurrentUser(request);
			if(stu != null){
				ActionUtil.UserLogout(request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Response(Status.ERROR);
		}
		return new Response(Status.SUCCESS);
	}
}
