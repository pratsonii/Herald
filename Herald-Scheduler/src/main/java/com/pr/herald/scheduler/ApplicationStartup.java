package com.pr.herald.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	EventFinishTask eventFinishTask;
	
	@Autowired
	ReactionTask reactionTask;
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) 
	{
		log.info("---- On App Start ----");
		
		eventFinishTask.startTask(eventFinishTask);
		reactionTask.startTask(reactionTask);
	}

}
