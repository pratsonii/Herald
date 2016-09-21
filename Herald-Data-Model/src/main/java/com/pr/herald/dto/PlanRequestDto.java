package com.pr.herald.dto;

import com.pr.herald.models.Plans;

import io.swagger.annotations.ApiModel;

@ApiModel
public class PlanRequestDto 
{
	private String name;
	private Long life;
	private Double price;
	private Long impect;
	
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
	
	public Plans convertToModel(Plans p)
	{
		if(p == null)
		{
			p = new Plans();
		}
		
		p.setName(name);
		p.setPrice(price);
		p.setLife(life);
		p.setImpect(impect);
		
		return p;
	}
}
