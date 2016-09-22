package com.pr.herald.service;

import java.util.List;

import com.pr.herald.dto.DeviceRequestDto;
import com.pr.herald.dto.EventReactionDto;
import com.pr.herald.dto.EventRequestDto;
import com.pr.herald.models.Events;

public interface EventServ 
{
	void addEvent(Events e);
	
	void updateEvent(EventRequestDto dto);
	
	void updateReaction(EventReactionDto dto);
	
	List<Events> getNearByEvents(Double lng,
								 Double lat, 
								 String category, 
								 Long distance);
	
	List<Events> getUserEvents(String mailId);
	
	List<Events> upgradeToFeatured();
	
	List<Events> deActivateDislikedEvents() ;
}
