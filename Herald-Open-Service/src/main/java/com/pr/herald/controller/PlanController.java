package com.pr.herald.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.base.RespEntity;
import com.pr.herald.contants.Constants;
import com.pr.herald.dto.EventResponseDto;
import com.pr.herald.dto.PlanResponseDto;
import com.pr.herald.models.Plans;
import com.pr.herald.service.PlanServ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/plan")
@Api(value="Plan Controller")
public class PlanController 
{
	@Autowired
	PlanServ planServ;
	
	@ApiOperation("Get All Plans")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<PlanResponseDto>>> getAllPlans()
	{
		List<Plans> plans = planServ.findAll();
		PlanResponseDto dto = new PlanResponseDto();
		List<PlanResponseDto> dtos = dto.convetToDto(plans);
		RespEntity<List<PlanResponseDto>> resp = new RespEntity(dtos, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
		
	}
}
