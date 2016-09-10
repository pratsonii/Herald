package com.pr.herald.service;

import com.pr.herald.dto.UserRequestDto;
import com.pr.herald.models.User;

public interface UserServ 
{
	void updateUser(UserRequestDto dto);
	User getUserByUsername(String email);
	boolean validateMail(User user);
}
