package com.swjtu.cn.service;

import com.swjtu.cn.bean.Admin;

public interface AdminService extends BaseService<Admin> {

	int checklogin(Admin admin);
}
