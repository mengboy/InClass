package com.swjtu.cn.service;

import java.util.List;

import com.swjtu.cn.bean.Apply;
import com.swjtu.cn.common.QueryBase;

public interface ApplyService extends BaseService<Apply> {
	
	//学生查找申请
	List<Apply> selectBystu(int number);
	
	//管理员查找申请
	int selectByAdmin(QueryBase querybase);
}
