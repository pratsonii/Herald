package com.pr.herald.service;

import com.pr.herald.base.BaseException;
import com.pr.herald.dto.UserRequestDto;
import com.pr.herald.models.User;

public interface UserServ 
{
	void updateUser(UserRequestDto dto);
	User getUserByUsername(String email);
	void validateMail(String mail) throws BaseException;
	void registerUser(User u) throws BaseException;
}
