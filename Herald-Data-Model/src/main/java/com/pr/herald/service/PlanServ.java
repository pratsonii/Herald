package com.pr.herald.service;

import java.util.List;

import com.pr.herald.models.Plans;

public interface PlanServ 
{
	void addPlan(Plans plan);
	
	public List<Plans> findAll();
}
