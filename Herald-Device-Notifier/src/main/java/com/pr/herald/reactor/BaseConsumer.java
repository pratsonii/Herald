package com.pr.herald.reactor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pr.herald.service.NotificationServ;

import reactor.core.Reactor;
import reactor.event.Event;
import reactor.spring.annotation.Consumer;
import reactor.spring.annotation.ReplyTo;
import reactor.spring.annotation.Selector;

@Consumer
public class BaseConsumer 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public Reactor reactor;
	
	@Autowired
	NotificationServ notificationServ;

	@Selector("deviceNotification")
	public void handleTestTopic(Event<NotificationData> evt) 
	{
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("---- in Device Notification consumer----");
		String eventId =(String) evt.getData().getData(DATA_TYPE.eventId);
		log.info("Event Id: "+ eventId);
		notificationServ.notifyDevices(eventId);
	}

}

