package com.pr.herald.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.dao.DevicesDao;
import com.pr.herald.models.Devices;
import com.pr.herald.service.DeviceServ;

@Service
@Transactional
public class DeviceImpl implements DeviceServ 
{
	@Autowired
	DevicesDao dao;
	
	@Override
	public void upsertDevice(Devices d) 
	{
		d = dao.save(d);
		if(d.getCreatedDate() == null)
		{
			d.setCreatedDate(new Date());
			d = dao.save(d);
		}
	}
}
