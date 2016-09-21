package com.pr.herald.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

@Component
public class TestTask extends TimerTask {

	private final static long ONCE_PER_MIN = 1000*20;
	
	@Override
	public void run() 
	{
		System.out.println("-- in run");
	}


	public void startTask(TestTask task)
	{
		System.out.println("--- Start Event Scheduler ---");
        Timer timer = new Timer();  
        timer.schedule(task, new Date(), ONCE_PER_MIN);// for your case u need to give 1000*60*60*24
    }

}
