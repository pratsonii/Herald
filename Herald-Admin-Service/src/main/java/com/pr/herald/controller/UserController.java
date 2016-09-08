package com.pr.herald.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.base.BaseException;
import com.pr.herald.contants.Constants;
import com.pr.herald.dto.UserRequestDto;
import com.pr.herald.service.UserServ;

@RestController
@RequestMapping("user")
public class UserController 
{
	@Autowired
	UserServ user;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity addCategory(@RequestBody UserRequestDto dto) throws BaseException
	{
		user.updateUser(dto);
		return new ResponseEntity<String>(Constants.updatSuccess, HttpStatus.OK);
	}
}
