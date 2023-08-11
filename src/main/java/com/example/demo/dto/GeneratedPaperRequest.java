package com.example.demo.dto;

public class GeneratedPaperRequest {
	private String email;
	private String title;
	
	public GeneratedPaperRequest() {
		
	}
	
	public GeneratedPaperRequest(String email, String title) {
		super();
		this.email = email;
		this.title = title;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
