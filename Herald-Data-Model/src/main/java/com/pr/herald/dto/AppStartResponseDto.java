package com.pr.herald.dto;

import java.util.List;

public class AppStartResponseDto 
{
	private List<EventResponseDto> events;
	private List<CategoryResponseDto> categories;
	
	public List<EventResponseDto> getEvents() {
		return events;
	}
	public void setEvents(List<EventResponseDto> events) {
		this.events = events;
	}
	public List<CategoryResponseDto> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryResponseDto> categories) {
		this.categories = categories;
	}
	
	
}
