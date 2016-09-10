package com.pr.herald.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.pr.herald.models.Events;
import com.pr.herald.models.Location;

import io.swagger.annotations.ApiModel;

@ApiModel
public class EventResponseDto 
{
	private String id;
	private Location location;
	private String header;
	private String description;
	private String status;
	private Long dislikes = 0L;
	private Long likes = 0L;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getDislikes() {
		return dislikes;
	}
	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
	}
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EventResponseDto convetToDto(Events e)
	{
		this.setId(e.getId());
		this.setHeader(e.getHeader());
		this.setDescription(e.getDescription());
		this.setLocation(e.getLocation());
		this.setLikes(e.getLikes());
		this.setDislikes(e.getDislikes());
		this.setStatus(e.getStatus());
		
		return this;
	}
	
	public List<EventResponseDto> convetToDto(List<Events> events)
	{
		return events.stream().map( e -> convetToDto(e)).collect(Collectors.toList());
	}
}
