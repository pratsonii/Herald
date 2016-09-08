package com.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HeraldUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeraldUserServiceApplication.class, args);
	}
}
