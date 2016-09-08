package com.pr.herald.dto;

public class AuthResponseDto  {

	private static final long serialVersionUID = -6624726180748515507L;
	private String token;

	public AuthResponseDto() {
		super();
	}

	public AuthResponseDto(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
