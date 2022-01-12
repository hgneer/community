package com.aipynux.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}
	/*
	这个类除了帮助启动Tomcat，Spring 容器也在该方法底层被自动创建，创建之后Spring容器会自动扫描某些包下的某些Bean并装到容器。本质上是一个配置类。
	* */

}
