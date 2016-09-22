package com.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

@SpringBootApplication
public class HeraldDeviceNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeraldDeviceNotifierApplication.class, args);
	}
	
	 @Bean
	  public Reactor reactor(Environment env) {
	    // implicit Environment is injected into bean def method
	    return Reactors.reactor().env(env).get();
	  }
}
