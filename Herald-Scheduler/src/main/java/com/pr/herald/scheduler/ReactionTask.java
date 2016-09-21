package com.pr.herald.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ReactionTask extends TimerTask 
{
	Logger log = Logger.getLogger(this.getClass());
	
	private final static long ONCE_PER_TWO_HOUR = 1000*60*60*2;

	@Override
	public void run() 
	{
		log.info("--- Before Reaction delay---");
		try {
			Thread.sleep(ONCE_PER_TWO_HOUR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("--- Start Reaction Execution ---");
	}

	public void startTask(ReactionTask task)
	{
		System.out.println("--- Start Reaction Scheduler ---");
        Timer timer = new Timer();  
        timer.schedule(task, new Date(), ONCE_PER_TWO_HOUR);// for your case u need to give 1000*60*60*24
    }

}
