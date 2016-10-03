package com.pr.herald.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.base.RespEntity;
import com.pr.herald.contants.Constants;
import com.pr.herald.dto.AppStartResponseDto;
import com.pr.herald.dto.DeviceRequestDto;
import com.pr.herald.models.Devices;
import com.pr.herald.service.DeviceServ;
import com.pr.herald.service.EventServ;
import com.pr.herald.service.StartUpServ;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("startUp")
@Api("Start up Controller")
public class StartUpController 
{	
	@Autowired
	DeviceServ deviceServ;
	
	@Autowired
	EventServ eventServ;
	
	@Autowired
	StartUpServ start;
	
	@RequestMapping(value = "info", method = RequestMethod.POST)
	public ResponseEntity<RespEntity<AppStartResponseDto>> start(@RequestBody DeviceRequestDto dto)
	{
		Devices d = deviceServ.upsertDevice(dto.convertToDto(null));
		AppStartResponseDto returnDto = eventServ.startUpEvent(d);
		RespEntity<AppStartResponseDto> resp = new RespEntity(returnDto, Constants.retriveSuccess);
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "test", method = RequestMethod.POST)
	public ResponseEntity test(@RequestBody DeviceRequestDto dto) throws FileNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		start.loadInitialData();
		return new ResponseEntity( HttpStatus.OK);
	}
}
