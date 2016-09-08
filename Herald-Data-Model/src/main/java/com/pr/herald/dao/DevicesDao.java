package com.pr.herald.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Devices;

public interface DevicesDao extends MongoRepository<Devices, Long> {

}

