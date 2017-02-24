package com.swjtu.cn.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.cn.bean.Admin;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.mapper.AdminMapper;
import com.swjtu.cn.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	Log logger = LogFactory.getLog(AdminServiceImpl.class);
	@Autowired
	AdminMapper adminMapper;

	@Override
	public int add(Admin obj) {
		// TODO Auto-generated method stub
		if(this.adminMapper.insertSelective(obj) > 0){
			logger.info("添加admin成功");
			return Status.SUCCESS;
		}else{
			logger.warn("添加admin失败，id:"+obj != null?obj.getId():"");
			return Status.ERROR;
		}
	}

	@Override
	public int update(Admin obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Admin obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Admin get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checklogin(Admin admin) {
		// TODO Auto-generated method stub
		if(this.adminMapper.checklogin(admin)!=null){
			logger.info("admin登录成功");
			return Status.SUCCESS;
		}else{
			logger.warn("admin登录失败，id:"+admin != null?admin.getId():"");
			return Status.USER_IS_NULL;
		}
	}
}
