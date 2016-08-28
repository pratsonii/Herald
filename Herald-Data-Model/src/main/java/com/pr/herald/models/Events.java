package com.pr.herald.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_H_Events")
public class Events {

	private Long id;
	private Devices devices;
	private Long lng;
	private Long lat;
	private String header;
	private String description;
	private User user;
	private Long notifiedTo;
	private Categories category;
	private String status;
	private String city;
	private String state;
	private String country;
	
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "device_token")
	public Devices getDevices() {
		return devices;
	}
	public void setDevices(Devices devices) {
		this.devices = devices;
	}
	
	@Column(name = "long")
	public Long getLng() {
		return lng;
	}
	public void setLng(Long lng) {
		this.lng = lng;
	}
	
	@Column(name = "lat")
	public Long getLat() {
		return lat;
	}
	public void setLat(Long lat) {
		this.lat = lat;
	}
	
	@Column(name = "header")
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	@Column(name = "desc")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "user")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "notified_to")
	public Long getNotifiedTo() {
		return notifiedTo;
	}
	public void setNotifiedTo(Long notifiedTo) {
		this.notifiedTo = notifiedTo;
	}
	
	@Column(name = "category")
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}
