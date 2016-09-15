package com.herald;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.herald.scheduler.EventFinishTask;

@SpringBootApplication
public class HeraldSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeraldSchedulerApplication.class, args);
		EventFinishTask.startTask();
	}
}
