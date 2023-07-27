package com.example.demo.dto;

public class ContributedQuestionsDto {
	private String question;
	private String topic;
	private int marks;
	
	public ContributedQuestionsDto() {
		
	}

	public ContributedQuestionsDto(String question, String topic, int marks) {
		super();
		this.question = question;
		this.topic = topic;
		this.marks = marks;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
}
