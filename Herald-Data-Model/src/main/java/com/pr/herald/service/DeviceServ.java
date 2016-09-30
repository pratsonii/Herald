package com.pr.herald.service;

import com.pr.herald.dto.DeviceRequestDto;
import com.pr.herald.models.Devices;

public interface DeviceServ 
{
	Devices upsertDevice(Devices d);
	
	void addFavorite(DeviceRequestDto dto);
	
	void removeFavorite(DeviceRequestDto dto);
}
