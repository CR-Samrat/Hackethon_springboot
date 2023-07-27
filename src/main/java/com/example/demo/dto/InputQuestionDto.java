package com.example.demo.dto;

public class InputQuestionDto {
	private String email;
	
	private String question;
	
	private String subject;
	
	private String topic;
	
	public InputQuestionDto() {
		
	}

	public InputQuestionDto(String email, String question, String subject, String topic) {
		super();
		this.email = email;
		this.question = question;
		this.subject = subject;
		this.topic = topic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
