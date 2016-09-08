package com.pr.herald.service;

import com.pr.herald.models.User;

public interface UserService {
	User getUserByUsername(String email);
}
