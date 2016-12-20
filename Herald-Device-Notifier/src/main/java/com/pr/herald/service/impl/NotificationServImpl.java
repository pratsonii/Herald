package com.pr.herald.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.android.Notification;
import com.pr.herald.dao.EventsDao;
import com.pr.herald.dao.PlansDao;
import com.pr.herald.dao.impl.DeviceDaoImpl;
import com.pr.herald.dto.EventResponseDto;
import com.pr.herald.models.Devices;
import com.pr.herald.models.Events;
import com.pr.herald.models.Plans;
import com.pr.herald.service.NotificationServ;

@Service
@Transactional
public class NotificationServImpl implements NotificationServ 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	EventsDao eventDao;
	
	@Autowired
	DeviceDaoImpl deviceDaoImpl;
	
	@Autowired
	PlansDao planDao;
	
	@Override
	public void notifyDevices(String eventId) throws IOException, ParseException 
	{
		Events event = eventDao.findOne(eventId);
		Plans p = planDao.findOne(event.getPlanId());
		List<Devices> devices = deviceDaoImpl.findDevicesToNotify(event.getLocation().getCoordinates()[0], 
																  event.getLocation().getCoordinates()[1], 
																  p.getImpect(), 
																  event.getCategoryName());
		
		log.info("No of Devices Found: "+devices.size()+" For Notification");
		if(devices.size() > 0)
		{
			List<String> deviceTokens = devices.stream().map( d -> d.getDeviceToken()).collect(Collectors.toList());
			
			Notification n = new Notification(deviceTokens, event.getHeader(), event.getDescription());
			JSONObject response = n.send();
			Long notifyCount = (Long) response.get("success");			
			
			log.info("No of devices notified: "+notifyCount);
			event.setNotifiedTo(Long.valueOf(notifyCount));
			eventDao.save(event);
		}
	}

}
