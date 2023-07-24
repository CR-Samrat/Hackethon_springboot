package com.example.demo.exception;

public class EmailAlreadyExistException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;

	public EmailAlreadyExistException(String email) {
		super(String.format("Email already exists with name '%s'",email));
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
