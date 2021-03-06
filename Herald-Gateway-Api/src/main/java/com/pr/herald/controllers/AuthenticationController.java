package com.pr.herald.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.base.BaseException;
import com.pr.herald.base.RespEntity;
import com.pr.herald.contants.Constants;
import com.pr.herald.dao.UserDao;
import com.pr.herald.dto.AuthRequestDto;
import com.pr.herald.dto.AuthResponseDto;
import com.pr.herald.dto.UserRegisterDto;
import com.pr.herald.models.SecurityUser;
import com.pr.herald.models.User;
import com.pr.herald.service.UserServ;
import com.pr.herald.utils.TokenUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("${javatab.route.authentication}")
@Api
public class AuthenticationController 
{
  @Value("${javatab.token.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserDao userDao;
  
  @Autowired
  private UserServ userServ;
  
  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @ApiOperation("User Authentication")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> authenticationRequest(@RequestBody AuthRequestDto authenticationRequest, Device device) 
		  throws AuthenticationException {

    // Perform the authentication
    Authentication authentication = this.authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-authentication so we can generate token
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String token = this.tokenUtils.generateToken(userDetails, device);

    // Return the token
    return ResponseEntity.ok(new AuthResponseDto(token));
  }

  @RequestMapping(value = "${javatab.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
    String token = request.getHeader(this.tokenHeader);
    String username = this.tokenUtils.getUsernameFromToken(token);
    SecurityUser user = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
    if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
      String refreshedToken = this.tokenUtils.refreshToken(token);
      return ResponseEntity.ok(new AuthResponseDto(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  	@ApiOperation("User Registration")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto dto) throws AuthenticationException, BaseException 
    {	  	
	  	User user = dto.convertToModel(null);

		User newUser = new User(user.getName(), 
								user.getMailId(), 
								passwordEncoder.encode(user.getPassword()),  
								null, 
								user.getRole());
		userServ.registerUser(newUser);
		
        return new ResponseEntity(new RespEntity(null, Constants.registerSuccess), HttpStatus.OK);
    }

}
