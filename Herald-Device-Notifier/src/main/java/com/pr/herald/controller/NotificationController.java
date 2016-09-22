package com.pr.herald.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pr.herald.reactor.DATA_TYPE;
import com.pr.herald.reactor.Event;
import com.pr.herald.reactor.NotificationData;
import com.pr.herald.reactor.Publisher;


@RestController
@RequestMapping("/notification")
public class NotificationController 
{
	@Autowired
	Publisher publisher;
	
	@RequestMapping("/event")
	public void sendNotification(@RequestParam String eventId)
	{
		Map<String,Object> data = new HashMap<>();
		data.put(DATA_TYPE.eventId.name(), eventId);
		NotificationData nData = new NotificationData();
		nData.setEvent(Event.deviceNotification);
		nData.setData(data);
		publisher.fireEvent(nData);
	}
}
