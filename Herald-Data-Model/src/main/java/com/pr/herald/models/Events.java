package com.pr.herald.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Events {

	private String id;
	private String deviceToken;
	
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Location location;
	
	@TextIndexed private String header;
	@TextIndexed private String description;
	private String userMailId;// @Id of users table
	private Long notifiedTo;
	private Set<String> categoryName = new HashSet<>();
	private String status;
	private String city;
	private String state;
	private String country;
	private Set<String> dislikedByDevice = new HashSet<>();
	private Set<String> likedByDevice = new HashSet<>();
	private Long dislikes = 0L;
	private Long likes = 0L;
	private String planId;
	private Long life;
	
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	
	public Set<String> getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(Set<String> categoryName) {
		this.categoryName = categoryName;
	}
	
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
	
	public Long getNotifiedTo() {
		return notifiedTo;
	}
	public void setNotifiedTo(Long notifiedTo) {
		this.notifiedTo = notifiedTo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Set<String> getDislikedByDevice() {
		return dislikedByDevice;
	}
	public void setDislikedByDevice(Set<String> dislikedByDevice) {
		this.dislikedByDevice = dislikedByDevice;
	}
	
	public Set<String> getLikedByDevice() {
		return likedByDevice;
	}
	public void setLikedByDevice(Set<String> likedByDevice) {
		this.likedByDevice = likedByDevice;
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
	
	public String getUserMailId() {
		return userMailId;
	}
	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
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
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public Long getLife() {
		return life;
	}
	public void setLife(Long life) {
		this.life = life;
	}
}
