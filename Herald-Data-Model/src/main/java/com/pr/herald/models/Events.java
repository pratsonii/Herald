package com.pr.herald.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
//@Table(name = "T_H_Events")
@Document(collection = "T_H_Events")
public class Events {

	private Long id;
	private String deviceToken;
	private Long lng;
	private Long lat;
	private String header;
	private String description;
	private String userMailId;// @Id of users table
	private Long notifiedTo;
	private String categoryName;
	private String status;
	private String city;
	private String state;
	private String country;
//	private List<String> dislikedByDevice;
//	private List<String> likedByDevice;
	private Long dislikes;
	private Long likes;
	
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	
	
	
	@org.springframework.data.annotation.Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
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
	
//	public List<String> getDislikedByDevice() {
//		return dislikedByDevice;
//	}
//	public void setDislikedByDevice(List<String> dislikedByDevice) {
//		this.dislikedByDevice = dislikedByDevice;
//	}
//	
//	public List<String> getLikedByDevice() {
//		return likedByDevice;
//	}
//	public void setLikedByDevice(List<String> likedByDevice) {
//		this.likedByDevice = likedByDevice;
//	}
	
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
	
}
