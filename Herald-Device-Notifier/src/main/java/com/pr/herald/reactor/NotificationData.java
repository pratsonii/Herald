package com.pr.herald.reactor;

import java.util.Map;

public class NotificationData 
{
	private String status;
	private Event event;
	private Map<String,Object> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public Object getData(DATA_TYPE key)
	{
		return data.get(key.name());
	}
	
}
