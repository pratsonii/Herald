package com.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pr.herald.scheduler.EventFinishTask;
import com.pr.herald.scheduler.ReactionTask;

@SpringBootApplication
public class HeraldSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeraldSchedulerApplication.class, args);
	}
}
