package com.pr.herald.serviceimpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
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
public class UserImpl implements UserServ 
{
	Logger log  = Logger.getLogger(this.getClass());
	
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
        	log.info("---"+e.getMessage()+"---");
        	throw new BaseException("Email Address  '"+user.getMailId()+"' is already registerd with us!");
        }
		
	}
}
