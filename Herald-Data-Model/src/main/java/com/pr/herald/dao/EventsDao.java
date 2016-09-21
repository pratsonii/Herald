package com.pr.herald.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pr.herald.models.Events;


public interface EventsDao extends MongoRepository<Events, String> 
{
	List<Events> findByUserMailIdOrderByStatusAscCreatedDateDesc(String mailId);
}
