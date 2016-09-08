package com.pr.herald.factory;



import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;


import com.pr.herald.models.SecurityUser;
import com.pr.herald.models.User;

public class UserFactory {

  public static SecurityUser create(User user) {
    return new SecurityUser(
      user.getMailId(),
      user.getPassword(),
      getAuthority(user)
    );
  }
  

	
	public static List<GrantedAuthority> getAuthority(User user) {
      return AuthorityUtils.createAuthorityList(user.getRole());
 }

}
