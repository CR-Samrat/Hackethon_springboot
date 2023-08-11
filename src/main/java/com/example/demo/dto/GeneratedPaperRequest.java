package com.example.demo.dto;

public class GeneratedPaperRequest {
	private String email;
	private String uuid;
	
	public GeneratedPaperRequest() {
		
	}
	
	public GeneratedPaperRequest(String email, String uuid) {
		super();
		this.email = email;
		this.uuid = uuid;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
