package com.chengxh.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chengxh.springbootdemo.mapper")
public class StartApplication {
    public static void main(String[] args){
    	
        SpringApplication.run(StartApplication.class, args);
    }
}
