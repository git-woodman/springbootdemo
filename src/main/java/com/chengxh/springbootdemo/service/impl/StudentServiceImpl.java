package com.chengxh.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengxh.springbootdemo.entity.Student;
import com.chengxh.springbootdemo.mapper.StudentMapper;
import com.chengxh.springbootdemo.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public Student getStuById(String sId) {
		return studentMapper.selectByPrimaryKey(sId);
	}
}
