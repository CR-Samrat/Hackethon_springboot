package com.example.demo.dto;

import java.util.List;

public class GenerateRequestDto {
	private String subject;
	private List<String> topic;
	private int full_marks;
	
	public GenerateRequestDto() {
		
	}

	public GenerateRequestDto(String subject, List<String> topic, int full_marks) {
		super();
		this.subject = subject;
		this.topic = topic;
		this.full_marks = full_marks;
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
	
	
}