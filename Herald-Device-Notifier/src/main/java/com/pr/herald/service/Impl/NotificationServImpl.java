package com.pr.herald.service.Impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.dao.EventsDao;
import com.pr.herald.dao.PlansDao;
import com.pr.herald.dao.impl.DeviceDaoImpl;
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
	public void notifyDevices(String eventId) 
	{
		Events event = eventDao.findOne(eventId);
		Plans p = planDao.findOne(event.getPlanId());
		List<Devices> devices = deviceDaoImpl.findDevicesToNotify(event.getLocation().getCoordinates()[0], 
																  event.getLocation().getCoordinates()[1], 
																  p.getImpect(), 
																  event.getCategoryName());
		log.info("No of devices notified: "+devices.size());
		event.setNotifiedTo(Long.valueOf(devices.size()));
		eventDao.save(event);
	}

}
