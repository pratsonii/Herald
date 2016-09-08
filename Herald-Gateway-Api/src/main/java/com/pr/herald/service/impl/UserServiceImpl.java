package com.pr.herald.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pr.herald.dao.UserDao;
import com.pr.herald.models.User;
import com.pr.herald.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByUsername(String email) 
	{
		return userDao.findByMailId(email);
	}

}