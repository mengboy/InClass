package com.swjtu.cn.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.cn.bean.Apply;
import com.swjtu.cn.common.QueryBase;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.mapper.ApplyMapper;
import com.swjtu.cn.service.ApplyService;

@Service
public class ApplyServiceImpl implements ApplyService {
	Log logger = LogFactory.getLog(ApplyServiceImpl.class);
	@Autowired
	ApplyMapper applyMapper;
	
	@Override
	public int add(Apply obj) {
		// TODO Auto-generated method stub
		if(this.applyMapper.insertSelective(obj) > 0){
			logger.info("添加申请成功");
			return Status.SUCCESS;
		}else{
			logger.warn("添加申请失败");
			return Status.ERROR;
		}
	}

	@Override
	public int update(Apply obj) {
		// TODO Auto-generated method stub
		if(this.applyMapper.updateByPrimaryKeySelective(obj) > 0){
			logger.info("更新成功");
			return Status.SUCCESS;
		}else{
			logger.warn("更新失败");
			return Status.ERROR;
		}
	}

	@Override
	public int delete(Apply obj) {
		// TODO Auto-generated method stub
		if(this.applyMapper.deleteByPrimaryKey(obj.getId()) > 0){
			logger.info("删除成功");
			return Status.SUCCESS;
		}else{
			logger.warn("删除失败");
			return Status.ERROR;
		}
	}

	@Override
	public Apply get(int id) {
		// TODO Auto-generated method stub
		return this.applyMapper.selectByPrimaryKey(id);
	}

	//学生查找申请
	@Override
	public List<Apply> selectBystu(int number) {
		// TODO Auto-generated method stub
		return this.applyMapper.selectBystu(number);
	}

	//管理员查找申请，分页
	@Override
	public int selectByAdmin(QueryBase querybase) {
		// TODO Auto-generated method stub
		try {
			querybase.setTotalRow(this.applyMapper.selectcountByAdmin(querybase));
			querybase.setResults(this.applyMapper.selectByAdmin(querybase));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Status.ERROR;
		}
		return Status.SUCCESS;
	}
	
}
