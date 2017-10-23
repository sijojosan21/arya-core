package com.sijojosan.arya.core.domain.entities;

public class BaseUser {

	private String id;
	private String userName;
	private String AuthorizationToken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthorizationToken() {
		return AuthorizationToken;
	}

	public void setAuthorizationToken(String authorizationToken) {
		AuthorizationToken = authorizationToken;
	}



}
