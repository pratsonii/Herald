package com.pr.herald.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.geo.Point;

import com.mongodb.client.model.geojson.Position;
import com.pr.herald.contants.Constants.LocationType;
import com.pr.herald.models.Devices;
import com.pr.herald.models.Location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DeviceRequestDto 
{
	private String deviceToken;
	private Double lng;
	private Double lat;
	private String userMailId;// @Id of users table
	private String favCategories;
	private Long distance;
	
	@ApiModelProperty
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	@ApiModelProperty
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	@ApiModelProperty
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@ApiModelProperty
	public String getUserMailId() {
		return userMailId;
	}
	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}
	
	public String getCategory() {
		return favCategories;
	}
	public void setCategory(String favCategories) {
		this.favCategories = favCategories;
	}
	
	public Long getDistance() {
		return distance;
	}
	public void setDistance(Long distance) {
		this.distance = distance;
	}
	
	public Devices convertToDto(Devices d)
	{
		if(d == null)
		{
			d = new Devices();
		}
		
		d.setDeviceToken(deviceToken);
		d.setLocation( new Point(lng, lat) );		
//		d.setLocation( new Location(LocationType.point, new Double[]{lng,lat}) );		
		d.setUserMailId(userMailId);
		d.setUpdatedDate(new Date());
		
		return d;
	}
}
