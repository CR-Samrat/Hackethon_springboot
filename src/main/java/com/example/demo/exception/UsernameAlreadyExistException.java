package com.example.demo.exception;

public class UsernameAlreadyExistException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	public UsernameAlreadyExistException(String username) {
		super(String.format("Username already exists with name '%s'", username));
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
