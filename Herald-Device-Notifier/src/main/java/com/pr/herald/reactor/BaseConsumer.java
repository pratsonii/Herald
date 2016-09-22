package com.pr.herald.reactor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Selector("deviceNotification")
	public void handleTestTopic(Event<NotificationData> evt) 
	{
		log.info("---- in  consumer----");
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(evt.getData().getData(DATA_TYPE.eventId));
	}

}

