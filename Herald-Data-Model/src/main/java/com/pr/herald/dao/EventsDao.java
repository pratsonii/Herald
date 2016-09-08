package com.pr.herald.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Events;


public interface EventsDao extends MongoRepository<Events, Long> {

}
