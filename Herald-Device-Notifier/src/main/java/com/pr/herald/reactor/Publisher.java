package com.pr.herald.reactor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.Reactor;
import reactor.event.Event;

@Component
public class Publisher 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private Reactor reactor;

	public void fireEvent(NotificationData nData) 
	{
		log.info("--- In Publish");
		reactor.notify(nData.getEvent().name(), Event.wrap(nData));
		log.info("--- Out Publish");
	}
}
