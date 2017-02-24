package com.swjtu.cn.actionmanage;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Apply;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.service.ApplyService;

@RestController
public class ManageApplyAction {
	@Autowired
	ApplyService applyService;
	
	@RequestMapping("/manage/apply/update")
	public Object update(HttpServletRequest request,@RequestParam int check_status,@RequestParam(required=false) Integer classroom_id,@RequestParam int id){
		Apply apply = new Apply();
		apply.setCheckTime(new Date());
		apply.setCheckStatus(check_status);
		apply.setId(id);
		apply.setClassroomId(classroom_id);
		int status = this.applyService.update(apply);
		return new Response(status);
	}
	
}
