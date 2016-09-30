package com.pr.herald.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.dao.DevicesDao;
import com.pr.herald.dto.DeviceRequestDto;
import com.pr.herald.models.Devices;
import com.pr.herald.service.DeviceServ;

@Service
@Transactional
public class DeviceImpl implements DeviceServ 
{
	@Autowired
	DevicesDao dao;
	
	@Override
	public Devices upsertDevice(Devices d) 
	{
		Devices device = dao.findOne(d.getDeviceToken());
		
		if(device == null)
		{
			d.setCreatedDate(new Date());
			dao.save(d);
			device = d;
		}
		else
		{
			device.setUpdatedDate(new Date());
			dao.save(device);
		}
		
		return device;
	}

	@Override
	public void addFavorite(DeviceRequestDto dto) 
	{
		Devices d = dao.findOne(dto.getDeviceToken());
		d.getFavCategories().add(dto.getCategory()); 
		dao.save(d);
	}

	@Override
	public void removeFavorite(DeviceRequestDto dto) 
	{
		Devices d = dao.findOne(dto.getDeviceToken());
		d.getFavCategories().remove(dto.getCategory()); 
		dao.save(d);
	}
}
