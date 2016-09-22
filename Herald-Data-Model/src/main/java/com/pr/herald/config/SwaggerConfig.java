package com.pr.herald.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Bean
    public Docket newsApi() 
	{
		log.info("-- Swagger bean creation ---");
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("greetings")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/.*"))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }
}
