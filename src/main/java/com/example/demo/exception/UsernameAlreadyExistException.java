package com.example.demo.exception;

public class UsernameAlreadyExistException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String resource;

	public UsernameAlreadyExistException(String username, String resource) {
		super(String.format("%s already exists with name '%s'", resource,username));
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
