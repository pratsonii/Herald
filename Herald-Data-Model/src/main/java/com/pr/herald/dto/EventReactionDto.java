package com.pr.herald.dto;

import com.pr.herald.contants.Constants.EventReaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EventReactionDto 
{
	private String eventId;
	private String deviceToken;
	private String reaction;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	@ApiModelProperty(notes = "pass "+EventReaction.like+" or "+ EventReaction.dislike)
	public String getReaction() {
		return reaction;
	}
	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
}
