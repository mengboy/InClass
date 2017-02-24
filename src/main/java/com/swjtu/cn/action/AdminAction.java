package com.swjtu.cn.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Admin;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.service.AdminService;
import com.swjtu.cn.utils.ActionUtil;

@RestController
public class AdminAction {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/inclass/api/admin/login")
	public Object login(HttpServletRequest request,@RequestParam String login_name,@RequestParam String password){
		Admin admin = new Admin();
		admin.setLoginName(login_name);
		admin.setPassword(password);
		System.out.println("admin"+admin == null);
		int status = this.adminService.checklogin(admin);
		if(status == 0)
			ActionUtil.setCurrentAdmin(request, admin);
		return new Response(status);
	}
	
	public static void main(String[] args) {
		
	}
}
