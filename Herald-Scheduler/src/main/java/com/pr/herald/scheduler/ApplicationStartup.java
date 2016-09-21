package com.pr.herald.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> 
{

	@Autowired
	TestTask task;
	
	@Autowired
	EventFinishTask eventFinishTask;
	
	@Autowired
	ReactionTask reactionTask;
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) 
	{
		System.out.println("On App Start");
//		task.startTask(task);
		
		eventFinishTask.startTask(eventFinishTask);
		reactionTask.startTask(reactionTask);
	}

}
