package com.pr.herald.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Plans;

public interface PlansDao extends MongoRepository<Plans, String> 
{
	List<Plans> findByStatus(String status);
}
