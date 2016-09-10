package com.pr.herald.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig 
{
	@Bean
    public Docket newsApi() {
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
