package com.pr.herald.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanFactory 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Bean
	public RestTemplate restTemplate()
	{
		log.info("-- RestTemplate bean creation ---");
		RestTemplate rt = new RestTemplate();
		return rt;
	}
}
