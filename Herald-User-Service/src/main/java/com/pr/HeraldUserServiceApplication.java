package com.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableSwagger2
public class HeraldUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeraldUserServiceApplication.class, args);
	}
}
