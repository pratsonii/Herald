package com.pr.herald.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Plans;

public interface PlansDao extends MongoRepository<Plans, String> {

}
