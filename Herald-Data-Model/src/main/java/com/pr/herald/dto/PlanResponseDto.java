package com.pr.herald.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.pr.herald.models.Plans;

import io.swagger.annotations.ApiModel;

@ApiModel
public class PlanResponseDto 
{

	private String id;
	private String name;
	private Long life;
	private Double price;
	private Long impact;
	
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
	public Long getImpact() {
		return impact;
	}
	public void setImpact(Long impact) {
		this.impact = impact;
	}
	

	public PlanResponseDto convetToDto(Plans e)
	{
		this.setId(e.getId());
		this.setImpact(e.getImpect());
		this.setLife(e.getLife());
		this.setName(e.getName());
		this.setPrice(e.getPrice());
		
		return this;
	}
	
	public List<PlanResponseDto> convetToDto(List<Plans> plans)
	{
		return plans.stream().map( p -> convetToDto(p)).collect(Collectors.toList());
	}
}
