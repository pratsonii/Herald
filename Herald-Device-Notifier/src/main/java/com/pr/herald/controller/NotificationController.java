package com.pr.herald.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/event", method = RequestMethod.POST)
	public ResponseEntity sendNotification(@RequestBody String eventId)
	{
		Map<String,Object> data = new HashMap<>();
		data.put(DATA_TYPE.EVENT_ID.name(), eventId);
		NotificationData nData = new NotificationData();
		nData.setEvent(Event.DEVICE_NOTIFICATION);
		nData.setData(data);
		publisher.fireEvent(nData);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
