package com.example.demo.dto;

public class QuestionResponse {
	private String message;
	private boolean status;
	
	public QuestionResponse() {
		
	}
	
	public QuestionResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
