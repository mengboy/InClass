package com.swjtu.cn.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.cn.bean.Student;
import com.swjtu.cn.mapper.StudentMapper;
import com.swjtu.cn.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	Log logger = LogFactory.getLog(StudentServiceImpl.class);
	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public int add(Student obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Student obj) {
		// TODO Auto-generated method stub
		if(this.studentMapper.updateByPrimaryKeySelective(obj) > 0){
			logger.info("更新stu成功");
		}else
			logger.info("更新stu失败");
		return 0;
	}

	@Override
	public int delete(Student obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student login(int stu_number,String password) {
		// TODO Auto-generated method stub
		Student stu = this.studentMapper.login(stu_number, password);
		if(stu != null)
			logger.info("登录成功");
		else
			logger.warn("登录失败");
		return stu;
	}

}
