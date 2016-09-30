package com.pr.herald.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pr.herald.contants.Constants.PlanStatus;
import com.pr.herald.dao.PlansDao;
import com.pr.herald.models.Plans;
import com.pr.herald.service.PlanServ;

@Service
public class PlanImpl implements PlanServ 
{
	@Autowired
	PlansDao dao;
	
	@Override
	public void addPlan(Plans plan) 
	{
		plan.setCreatedDate(new Date());
		plan.setUpdatedDate(new Date());
		
		dao.save(plan);
	}
	
	@Override
	public List<Plans> findAll() 
	{		
		return dao.findByStatus(PlanStatus.active);
	}

}
