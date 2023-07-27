package com.example.demo.dto;

public class GenerateResponseDto {
	private String question;
	private int marks;
	private String topic;
	
	public GenerateResponseDto() {
		
	}

	public GenerateResponseDto(String question, int marks, String topic) {
		super();
		this.question = question;
		this.marks = marks;
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
