package com.pr.herald.dto;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pr.herald.base.BaseException;
import com.pr.herald.contants.Constants;
import com.pr.herald.contants.Constants.LocationType;
import com.pr.herald.models.Events;
import com.pr.herald.models.Location;
import com.pr.herald.utility.CommonUtility;

import io.swagger.annotations.ApiModel;

@ApiModel
public class EventRequestDto 
{	@Autowired
	CommonUtility util;

	private String id;
	private String deviceToken;
	private Double lng;
	private Double lat;
	private String header;
	private String description;
	private String userMailId;// @Id of users table
	private String categoryName;
	private String city;
	private String state;
	private String country;
	private String planId;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
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
	public String getUserMailId() {
		return userMailId;
	}
	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public Events convertToModel(Events e)
	{
		if(e == null)
		{
			e = new Events();
		}
		
		e.setDeviceToken(deviceToken);
		e.setLocation( new Location(LocationType.point, new Double[]{lng,lat}) );
		e.setHeader(header);
		e.setDescription(description);
		e.setUserMailId(userMailId);
		e.getCategoryName().add(categoryName);
		e.setCity(city);
		e.setState(state);
		e.setCountry(country);
		e.setPlanId(planId);
		
		return e;
	}

	
	public void checkMandatoryFields() throws BaseException
	{		
		List<String> fields = Arrays.asList(this.getPlanId(), this.getCategoryName(), this.getHeader(), this.getDescription());
		util.checkEmpty(fields);
	}
}
