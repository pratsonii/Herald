package com.pr.herald.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_H_Devices")
public class Devices {
	
	private String deviceToken;
	private Long lng;
	private Long lat;
	private String userMailId;// @Id of users table
	private List<String> favCategories; // Favorite Categories
	
	@Id
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	public Long getLng() {
		return lng;
	}
	public void setLng(Long lng) {
		this.lng = lng;
	}
	
	public Long getLat() {
		return lat;
	}
	public void setLat(Long lat) {
		this.lat = lat;
	}
	
	public String getUserMailId() {
		return userMailId;
	}
	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}
	
	public List<String> getFavCategories() {
		return favCategories;
	}
	public void setFavCategories(List<String> favCategories) {
		this.favCategories = favCategories;
	}
	

}
