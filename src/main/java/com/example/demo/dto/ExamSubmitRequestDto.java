package com.example.demo.dto;

public class ExamSubmitRequestDto {
	private String email;
	private String subject;
	private int marks;
	
	public ExamSubmitRequestDto() {
		
	}
	
	public ExamSubmitRequestDto(String email, String subject, int marks) {
		super();
		this.email = email;
		this.subject = subject;
		this.marks = marks;
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
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
