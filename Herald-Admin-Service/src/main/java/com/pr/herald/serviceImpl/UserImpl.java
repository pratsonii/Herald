package com.pr.herald.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.dao.UserDao;
import com.pr.herald.dto.UserRequestDto;
import com.pr.herald.models.User;
import com.pr.herald.service.UserServ;

@Service
@Transactional
public class UserImpl implements UserServ {

	@Autowired
	UserDao dao;
	
	@Override
	public void updateUser(UserRequestDto dto) 
	{
		User u = dao.findOne(dto.getMailId());
		u = dto.convertToModel(u);
		
		dao.save(u);
	}

}
