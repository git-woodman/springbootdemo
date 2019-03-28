package com.chengxh.springbootdemo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.chengxh.springbootdemo.interceptor.ApiInterceptor;

@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new ApiInterceptor());
	}
	
}
