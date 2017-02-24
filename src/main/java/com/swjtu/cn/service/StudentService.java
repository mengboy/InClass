package com.swjtu.cn.service;

import com.swjtu.cn.bean.Student;

public interface StudentService extends BaseService<Student> {
	Student login(int stu_number,String password);
}
