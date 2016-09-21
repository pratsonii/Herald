package com.pr.herald.scheduler;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.pr.herald.contants.Constants.EventStatus;
import com.pr.herald.dao.impl.EventDaoImpl;
import com.pr.herald.service.EventServ;
@Component
public class EventFinishTask extends TimerTask 
{
	@Autowired
	EventDaoImpl daoImpl;
	
	@Autowired
	EventServ serv;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private final static long ONCE_PER_DAY = 1000*60*60*24;

	@Override
	public void run() 
	{
		log.info("--- Before Event delay---");
		try {
			Thread.sleep(ONCE_PER_DAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("--- Start OverDue Event Execution ---");
		List<Document> eventDocs = daoImpl.findOverdueEvents(EventStatus.active, new Date());
		
		eventDocs.forEach( e -> 
		{
			daoImpl.updateOverDueEvents((String) e.get("_id"), EventStatus.inActive);
		});
		
	}

	public void startTask(EventFinishTask task)
	{
		System.out.println("--- Start Event Scheduler ---");
        Timer timer = new Timer();  
        
        Date d = new Date();
        d.setHours(23);
        d.setMinutes(37);
        d.setSeconds(0);
        
        timer.schedule(task, d, ONCE_PER_DAY);
    }
}
