package com.pr.herald.dto;

public class AuthResponseDto  {

	private static final long serialVersionUID = -6624726180748515507L;
	private String Authorization;

	public AuthResponseDto() {
		super();
	}

	public AuthResponseDto(String Authorization) {
		this.setAuthorization(Authorization);
	}

	public String getAuthorization() {
		return this.Authorization;
	}

	public void setAuthorization(String Authorization) {
		this.Authorization = Authorization;
	}

}
