package com.herald.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class EventFinishTask extends TimerTask 
{
	Logger log = Logger.getLogger(this.getClass());
	
	private final static long ONCE_PER_DAY = 1000*60*60*24;
	private final static long ONCE_PER_MIN = 1000*5;

	@Override
	public void run() 
	{
		log.info("--- Start Executing Event ---");
		System.out.println("--- Start Event Finish Scheduler ---");
	}

	public static void startTask()
	{
		System.out.println("--- Start Event Finish Scheduler ---");
		EventFinishTask task = new EventFinishTask();
        Timer timer = new Timer();  
        timer.schedule(task, new Date(), ONCE_PER_MIN);// for your case u need to give 1000*60*60*24
    }
}
