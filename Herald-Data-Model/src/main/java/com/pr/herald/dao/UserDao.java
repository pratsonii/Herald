package com.pr.herald.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.User;

public interface UserDao extends MongoRepository<User, String> {
	public User findByMailId(String mailId);
}
