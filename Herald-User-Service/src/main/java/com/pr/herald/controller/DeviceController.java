package com.pr.herald.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.dto.DeviceRequestDto;
import com.pr.herald.service.DeviceServ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/device")
@Api(value="Device Controller")
public class DeviceController 
{
	@Autowired
	DeviceServ serv;
	
	@RequestMapping(value = "/upsert", method = RequestMethod.POST)
	@ApiOperation(value = "Update Device Info")
	public ResponseEntity upsertDevice(@RequestBody DeviceRequestDto dto)	
	{
		serv.upsertDevice(dto.convertToDto(null));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
