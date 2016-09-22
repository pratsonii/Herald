package com.pr.herald.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pr.herald.dao.EventsDao;
import com.pr.herald.dao.impl.DeviceDaoImpl;
import com.pr.herald.models.Events;
import com.pr.herald.service.NotificationServ;

public class NotificationServImpl implements NotificationServ 
{
	@Autowired
	EventsDao eventDao;
	
	@Autowired
	DeviceDaoImpl deviceDaoImpl;
	
	@Override
	public void notifyDevices(String eventId) 
	{
		Events event = eventDao.findOne(eventId);
		deviceDaoImpl.findDevicesToNotify(event.getLocation().getCoordinates()[0], 
										  event.getLocation().getCoordinates()[1], 
										  0L, 
										  "");
	}

}
