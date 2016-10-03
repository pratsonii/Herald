package com.pr.herald.base;

public class BaseException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private final String message;
	
	public BaseException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}	
}
