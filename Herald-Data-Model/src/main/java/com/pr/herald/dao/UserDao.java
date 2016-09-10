package com.pr.herald.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pr.herald.models.User;

public interface UserDao extends MongoRepository<User, String> 
{
	public User findByMailId(String mailId);
	
	@Query("{ 'mailId': ?0 }")
	List<User> selectByMail(String mailId);
}
