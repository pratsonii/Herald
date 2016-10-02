package com.pr.herald.dto;

import com.pr.herald.contants.Constants.UserRoles;
import com.pr.herald.models.User;

public class UserRegisterDto 
{

	private String name;
	private String mailId;
	private String password;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public User convertToModel(User u)
	{
		if(u == null)
		{
			u = new User();
		}
		
		u.setName(name);
		u.setMailId(mailId);
		u.setPassword(password);
		u.setPhone(phone);
		u.setRole(UserRoles.user);
		
		return u;
	}
}
