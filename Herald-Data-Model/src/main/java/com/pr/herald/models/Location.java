package com.pr.herald.models;

public class Location 
{
	private String type;
	private Double[] coordinates;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}
	
	
	public Location() {
		super();
	}
	public Location(String type, Double[] coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}
	
	
	
}
