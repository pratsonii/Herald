package com.pr.herald.controller;

import java.util.List;

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
import com.pr.herald.dto.EventReactionDto;
import com.pr.herald.dto.EventRequestDto;
import com.pr.herald.dto.EventResponseDto;
import com.pr.herald.models.Events;
import com.pr.herald.service.EventServ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("event")
@Api("Event Controller")
public class EventController 
{
	@Value("${event.notification.address}")
	private String notifierAddress;
	
	@Autowired
	EventServ serv;
	
	@Autowired
	RestTemplate rt;
	
	@ApiOperation("update event Reaction")
	@RequestMapping(value = "/updateReaction", method = RequestMethod.PUT)
	public ResponseEntity<RespEntity<EventResponseDto>> updateEventReaction(@RequestBody EventReactionDto dto) throws BaseException
	{
		EventResponseDto e = new EventResponseDto();
		EventResponseDto result = e.convetToDto(serv.updateReaction(dto));
		RespEntity<EventResponseDto> resp = new RespEntity(result, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@ApiOperation("Get near by events")
	@RequestMapping(value = "/nearEvents", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<EventResponseDto>>> getNearByEvents(@RequestParam Double lng1,
																			  @RequestParam Double lat1, 
																			  @RequestParam Double lng2, 
																			  @RequestParam Double lat2, 
																			  @RequestParam String category, 
																			  @RequestParam Double distance )
	{
		EventResponseDto e = new EventResponseDto();
		List<EventResponseDto> result = e.convetToDto(serv.getEventsWithinBox(lng1, lat1, lng2, lat2, category));
		RespEntity<List<EventResponseDto>> resp = new RespEntity<List<EventResponseDto>>(result, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@ApiOperation("Get near by events")
	@RequestMapping(value = "/nearEventsCircle", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<EventResponseDto>>> getNearByEvents(@RequestParam Double lng,
																			  @RequestParam Double lat, 
																			  @RequestParam String category, 
																			  @RequestParam Double distance )
	{
		EventResponseDto e = new EventResponseDto();
		List<EventResponseDto> result = e.convetToDto(serv.getNearByEvents(lng, lat, category, distance));
		RespEntity<List<EventResponseDto>> resp = new RespEntity<List<EventResponseDto>>(result, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@ApiOperation("Search near by events")
	@RequestMapping(value = "/searchEvents", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<EventResponseDto>>> searchByEvents(@RequestParam Double lng,
																			  @RequestParam Double lat, 
																			  @RequestParam String searchString, 
																			  @RequestParam Double distance )
	{
		EventResponseDto e = new EventResponseDto();
		List<EventResponseDto> result = e.convetToDto(serv.searchNearByEvents(lng, lat, searchString, distance));
		RespEntity<List<EventResponseDto>> resp = new RespEntity(result, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
	
	@ApiOperation("Get events by Id")
	@RequestMapping(value = "/getEvent", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<EventResponseDto>> getEvents(@RequestParam String eventId )
	{
		EventResponseDto e = new EventResponseDto();
		EventResponseDto result = e.convetToDto(serv.getEventByID(eventId));
		RespEntity<EventResponseDto> resp = new RespEntity(result, Constants.retriveSuccess);
		
		return new ResponseEntity(resp, HttpStatus.OK);
	}
}
