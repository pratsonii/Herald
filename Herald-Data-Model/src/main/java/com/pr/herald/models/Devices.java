package com.pr.herald.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_H_Devices")
public class Devices {

	
	private String deviceToken;
	private Long lng;
	private Long lat;
	private User user;
	
	@Id
	@Column(name = "device_token",length = 512)
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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
	
	@Column(name = "user")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
