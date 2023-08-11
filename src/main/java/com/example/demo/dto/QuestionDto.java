package com.example.demo.dto;

public class QuestionDto {
	private Long id;
	private String question;
	private int marks;
	private int full_marks;
	
	public QuestionDto() {
		
	}
	
	public QuestionDto(Long id, String question, int marks, int full_marks) {
		super();
		this.id = id;
		this.question = question;
		this.marks = marks;
		this.full_marks = full_marks;
	}

	public int getFull_marks() {
		return full_marks;
	}

	public void setFull_marks(int full_marks) {
		this.full_marks = full_marks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
