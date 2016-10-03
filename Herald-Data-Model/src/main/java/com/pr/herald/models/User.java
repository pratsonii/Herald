package com.pr.herald.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	
	private String name;
	private String mailId;
	private String password;
	private String phone;
	private String role;
	
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	
	
	@org.springframework.data.annotation.Id
//	@Column(name = "mail_id",length = 256)
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
	
//	@Column(updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
//	@Column(updatable = false)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public User(String name, String mailId, String password, String phone, String role) {
		super();
		this.name = name;
		this.mailId = mailId;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}
	public User() {
		super();
	}
	
	
}
