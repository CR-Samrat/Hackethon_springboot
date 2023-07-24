package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resource;

	public ResourceNotFoundException(String resource) {
		super(String.format("%s doesn't exists",resource));
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
}
