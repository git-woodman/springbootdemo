package com.chengxh.springbootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengxh.springbootdemo.entity.Student;
import com.chengxh.springbootdemo.model.Aliyun;
import com.chengxh.springbootdemo.model.TestDto;
import com.chengxh.springbootdemo.service.IStudentService;
import com.chengxh.springbootdemo.util.RedisUtil;

@Controller
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private Aliyun aliyun;
	
	@Autowired
	private IStudentService studnetService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping(value="getAliyun")
	@ResponseBody
	public Aliyun getAliyun(){
		return aliyun;
	}
	
	@RequestMapping(value="index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="getJson")
	@ResponseBody
	public TestDto getJson(@RequestBody TestDto reqBody){
		return reqBody;
	}
	
	@RequestMapping(value="getStu")
	@ResponseBody
	public Student getStu(String sId){
		
		return studnetService.getStuById(sId);
	}
	
	@RequestMapping(value="testRedis")
	@ResponseBody
	public String testRedis(String key, String value){
		redisUtil.set(key, value);
		return redisUtil.get(key).toString();
	}
}
