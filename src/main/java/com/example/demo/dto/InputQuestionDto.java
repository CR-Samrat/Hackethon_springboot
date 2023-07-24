package com.example.demo.dto;

public class InputQuestionDto {
	private String username;
	
	private String question;
	
	private String subject;
	
	private String topic;
	
	public InputQuestionDto() {
		
	}

	public InputQuestionDto(String username, String question, String subject, String topic) {
		super();
		this.username = username;
		this.question = question;
		this.subject = subject;
		this.topic = topic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
