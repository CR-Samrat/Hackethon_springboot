package com.example.demo.dto;

import java.util.List;

public class GenerateRequestDto {
	private String email;
	private String title;
	private String subject;
	private List<String> topic;
	private int full_marks;
	
	public GenerateRequestDto() {
		
	}

	public GenerateRequestDto(String email, String title ,String subject, List<String> topic, int full_marks) {
		super();
		
		this.title = title;
		this.email = email;
		this.subject = subject;
		this.topic = topic;
		this.full_marks = full_marks;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getTopic() {
		return topic;
	}

	public void setTopic(List<String> topic) {
		this.topic = topic;
	}

	public int getFull_marks() {
		return full_marks;
	}

	public void setFull_marks(int full_marks) {
		this.full_marks = full_marks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
