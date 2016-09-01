package com.pr.herald.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pr.herald.models.User;

public interface UserDao extends JpaRepository<User, String> {
	public User findByMailId(String mailId);
}
