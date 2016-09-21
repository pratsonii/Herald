package com.pr.herald.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.dto.PlanRequestDto;
import com.pr.herald.service.PlanServ;

@RestController
@RequestMapping("/plan")
public class PlanController 
{
	@Autowired
	PlanServ plan;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity addPlan(@RequestBody PlanRequestDto dto)
	{
		plan.addPlan(dto.convertToModel(null));
		
		return new ResponseEntity(HttpStatus.OK);
	}
}
