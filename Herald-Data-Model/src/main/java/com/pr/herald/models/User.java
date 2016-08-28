package com.pr.herald.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "T_H_User")
public class User {
	
	private Long id;
	private String name;
	private String mailId;
	private String password;
	private String phone;
	private String role;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name",length = 256)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "mail_id",length = 256)
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	@Column(name = "password",length = 256)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "phone",length = 256)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "role",length = 50)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
