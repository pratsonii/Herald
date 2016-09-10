package com.pr.herald.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
//@Table(name = "T_H_Devices")
@Document(collection = "T_H_Devices")
public class Devices {
	
	private String deviceToken;
	private Location location;
	private String userMailId;// @Id of users table
	private List<String> favCategories; // Favorite Categories
	
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	
	@org.springframework.data.annotation.Id
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
	
//	@Column(updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
//	@Column(updatable = false)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	

}
