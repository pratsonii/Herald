package com.pr.herald.service;

import java.util.List;

import com.pr.herald.base.BaseException;
import com.pr.herald.dto.AppStartResponseDto;
import com.pr.herald.dto.EventReactionDto;
import com.pr.herald.dto.EventRequestDto;
import com.pr.herald.dto.EventResponseDto;
import com.pr.herald.models.Devices;
import com.pr.herald.models.Events;

public interface EventServ 
{
	Events addEvent(Events e) throws BaseException;
	
	void updateEvent(EventRequestDto dto);
	
	void updateReaction(EventReactionDto dto);
	
	List<Events> getNearByEvents(Double lng,
								 Double lat, 
								 String category, 
								 Long distance);
	
	List<Events> searchNearByEvents(Double lng,
								 Double lat, 
								 String searchString, 
								 Long distance);
	
	List<Events> getUserEvents(String mailId);
	
	void upgradeToFeatured();
	
	List<EventResponseDto> addPlanToDto(List<EventResponseDto> dto);
	
	void deActivateDislikedEvents() ;
	
	AppStartResponseDto startUpEvent(Devices d);

	void reactivateEvent(String eventId) throws BaseException;

	void deleteEvent(String eventId);
}
