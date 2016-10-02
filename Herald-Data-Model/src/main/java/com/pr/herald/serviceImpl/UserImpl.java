package com.pr.herald.serviceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.base.BaseException;
import com.pr.herald.contants.Constants;
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

	@Override
	public User getUserByUsername(String email) 
	{
		return dao.findByMailId(email);
	}

	@Override
	public void validateMail(String email) throws BaseException 
	{
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches())
		{
			throw new BaseException(Constants.invalidEmail +" :"+ email);
		}
		
	}

	@Override
	public void registerUser(User user) throws BaseException 
	{
		validateMail(user.getMailId());
        try
        {
        	dao.insert(user);
        }
        catch(DuplicateKeyException e)
        {
        	throw new BaseException("Email Address  '"+user.getMailId()+"' is already registerd with us!");
        }
		
	}
}
