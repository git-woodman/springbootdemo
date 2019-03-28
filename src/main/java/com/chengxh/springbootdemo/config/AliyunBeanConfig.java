package com.chengxh.springbootdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.chengxh.springbootdemo.model.Aliyun;

@SpringBootConfiguration
public class AliyunBeanConfig {

	@Value("${aliyun.appKey}")
	private String appKey;
	@Value("${aliyun.appSecret}")
	private String appSecret;
	@Value("${aliyun.bucket}")
	private String bucket;
	@Value("${aliyun.endPoint}")
	private String endPoint;

	@Bean
    public Aliyun aliyun(){
        return Aliyun.options()
                .setAppKey(appKey)
                .setAppSecret(appSecret)
                .setBucket(bucket)
                .setEndPoint(endPoint)
                .build();
    }
}
