package com.pr.herald.dto;

import java.util.Date;

import com.pr.herald.models.User;

public class UserRequestDto 
{
	private String name;
	private String mailId;
	private String password;
	private String phone;
	private String role;
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
//	@Column(name = "name",length = 256)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
//	@Column(name = "password",length = 256)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	@Column(name = "phone",length = 256)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
//	@Column(name = "role",length = 50)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public User convertToModel(User u)
	{
		if(u == null)
		{
			u = new User();
		}
		
		u.setName(name);
		u.setPhone(phone);
		u.setUpdatedDate(new Date());
		return u;
	}
}
