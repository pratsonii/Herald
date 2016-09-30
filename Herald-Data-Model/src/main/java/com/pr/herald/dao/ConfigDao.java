package com.pr.herald.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Config;

public interface ConfigDao extends MongoRepository<Config, String> {

}
