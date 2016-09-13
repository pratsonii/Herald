package com.pr.herald.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plans")
public class Plans 
{
	private String id;
	private String name;
	private Long life;
	private Double price;
	private Long impect;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getLife() {
		return life;
	}
	public void setLife(Long life) {
		this.life = life;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getImpect() {
		return impect;
	}
	public void setImpect(Long impect) {
		this.impect = impect;
	}
}
