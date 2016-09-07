package com.pr.herald.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pr.herald.base.BaseException;
import com.pr.herald.base.ResponseEntinty;
import com.pr.herald.contants.Constants;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity handleNullPointerException(HttpServletRequest request, Exception ex)
	{
		log.info("--- NullPointerException Occured:: URL="+request.getRequestURL());
		return new ResponseEntity(new ResponseEntinty(null, Constants.noData), HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(BaseException.class)
	public ResponseEntity handleBaseException(HttpServletRequest request, Exception ex)
	{
		log.info("--- BaseException Occured:: URL="+request.getRequestURL());
		return new ResponseEntity(new ResponseEntinty(null, ex.getMessage()), HttpStatus.OK);
	}

	
	@ExceptionHandler(IdentifierGenerationException.class)
	public ResponseEntity handleIdentifierGenerationException(HttpServletRequest request, Exception ex)
	{
		log.info("--- IdentifierGenerationExceptionException Occured:: URL="+request.getRequestURL());
		return new ResponseEntity(new ResponseEntinty(null, ex.getMessage()), HttpStatus.OK);
	}
}
