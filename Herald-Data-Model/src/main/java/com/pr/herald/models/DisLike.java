package com.pr.herald.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_H_DisLike")
public class DisLike {
	
	private Long id;
	private Events events;
	private Devices devices;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Events getEvents() {
		return events;
	}
	public void setEvents(Events events) {
		this.events = events;
	}
	public Devices getDevices() {
		return devices;
	}
	public void setDevices(Devices devices) {
		this.devices = devices;
	}
	
	
}
