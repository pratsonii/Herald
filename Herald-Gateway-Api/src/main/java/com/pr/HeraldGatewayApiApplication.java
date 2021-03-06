package com.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
@EnableAspectJAutoProxy
public class HeraldGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeraldGatewayApiApplication.class, args);
	}
}
