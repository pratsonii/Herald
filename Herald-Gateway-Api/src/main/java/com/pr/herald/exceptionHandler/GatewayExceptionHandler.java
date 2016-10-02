package com.pr.herald.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pr.herald.base.RespEntity;
import com.pr.herald.contants.Constants;

@ControllerAdvice
public class GatewayExceptionHandler 
{
	Logger log = Logger.getLogger(this.getClass());

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity handleBadCredentialsException(HttpServletRequest request, Exception ex)
	{
		log.info("--- BadCredentialsException Occured:: URL="+request.getRequestURL());
		log.info(ex.getMessage());
		return new ResponseEntity(new RespEntity(null, Constants.invalidUserPass), HttpStatus.EXPECTATION_FAILED);
	}
	
}
