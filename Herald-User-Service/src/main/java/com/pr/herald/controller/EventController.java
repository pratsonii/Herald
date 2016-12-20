package com.pr.herald.controller;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pr.herald.base.BaseException;
import com.pr.herald.base.RespEntity;
import com.pr.herald.contants.Constants;
import com.pr.herald.dto.CategoryResponseDto;
import com.pr.herald.dto.EventRequestDto;
import com.pr.herald.dto.EventResponseDto;
import com.pr.herald.models.Categories;
import com.pr.herald.models.Events;
import com.pr.herald.service.CategoryServ;
import com.pr.herald.service.EventServ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("event")
@Api("Event Controller")
public class EventController 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Value("${event.notification.address}")
	private String notifierAddress;
	
	@Value("${cross.gateway}")
	private String cross;

	@Value("${javatab.token.header}")
	private String tokenHeader;
	
	@Autowired
	EventServ serv;
	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	CategoryServ categoryServ;
	
	@ApiOperation("add event")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity addEvent(@RequestBody EventRequestDto dto, HttpServletRequest request) throws BaseException
	{
		String email = (String) request.getHeader("email");
		log.info("--- User/Email :"+email+" ---");
		dto.setUserMailId(email);
		dto.checkMandatoryFields();
		Events e = serv.addEvent(dto.convertToModel(null));
		rt.postForObject(notifierAddress, e.getId(), ResponseEntity.class);
		return new ResponseEntity(new RespEntity(e.getId(), Constants.eventSuccess), HttpStatus.OK);
	}
	
	@ApiOperation("update event")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity updateEvent(@RequestBody EventRequestDto dto, HttpServletRequest request)
	{
		String email = (String) request.getHeader("email");
		log.info("--- User/Email :"+email+" ---");
		dto.setUserMailId(email);
		serv.updateEvent(dto);
		return new ResponseEntity(new RespEntity(null, Constants.updatSuccess), HttpStatus.OK);
	}
	
	@ApiOperation("Get all my events")
	@RequestMapping(value = "/myEvents", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<EventResponseDto>>> getMyEvents(HttpServletRequest request)
	{
		String email = (String) request.getHeader("email");
		log.info("--- User/Email :"+email+" ---");
		
		EventResponseDto e = new EventResponseDto();
		List<EventResponseDto> result = e.convetToDto(serv.getUserEvents(email));
		result = serv.addPlanToDto(result);
		RespEntity<List<EventResponseDto>> resp = new RespEntity(result, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@ApiOperation("Get all Categories")
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<CategoryResponseDto>>> getCategories()
	{
		List<Categories> categories = categoryServ.findCategoryExceptFeatured();
		
		List<CategoryResponseDto> categoryDto = categories.stream().map(  c -> 
		new CategoryResponseDto().convertToDto(c, new HashSet<>())).collect(Collectors.toList());
		
		RespEntity<List<CategoryResponseDto>> resp = new RespEntity(categoryDto, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@ApiOperation("reactivate event")
	@RequestMapping(value = "/reactivate", method = RequestMethod.PUT)
	public ResponseEntity reactivateEvent(@RequestParam String eventId) throws BaseException
	{
		serv.reactivateEvent(eventId);
		rt.postForObject(notifierAddress, eventId, ResponseEntity.class);
		return new ResponseEntity(new RespEntity(null, Constants.eventActivated), HttpStatus.OK);
	}
	
	@ApiOperation("delete event")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity deleteEvent(@RequestParam String eventId) throws BaseException
	{
		serv.deleteEvent(eventId);
		return new ResponseEntity(new RespEntity(null, Constants.eventDeleted), HttpStatus.OK);
	}
}
