package com.pr.herald.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pr.herald.service.EventServ;

@Component
public class ReactionTask extends TimerTask 
{
	@Autowired
	EventServ event;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private static final long ONCE_PER_TWO_HOUR = 1000*60*60*2;

	@Override
	public void run() 
	{
		log.info("--- Before Reaction delay---");
		try {
			Thread.sleep(ONCE_PER_TWO_HOUR);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		log.info("--- Start Reaction Execution ---");
		event.upgradeToFeatured();
		event.deActivateDislikedEvents();		
	}

	public void startTask(ReactionTask task)
	{
		log.info("--- Start Reaction Execution ---");
        Timer timer = new Timer();  
        timer.schedule(task, new Date(), ONCE_PER_TWO_HOUR);// for your case u need to give 1000*60*60*24
    }

}
