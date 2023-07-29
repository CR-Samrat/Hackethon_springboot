package com.example.demo.dto;

import java.util.List;

public class UserDetailsDto {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String expert;
	private List<String> contributedQuestions;
	private List<String> generatedPapers;
	private List<Integer> marks;
	
	public UserDetailsDto() {
		
	}
	
	public UserDetailsDto(String username, String firstName, String lastName, String email, String expert,
			List<String> contributedQuestions, List<String> generatedPapers, List<Integer> marks) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.expert = expert;
		this.contributedQuestions = contributedQuestions;
		this.generatedPapers = generatedPapers;
		this.marks = marks;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpert() {
		return expert;
	}

	public void setExpert(String expert) {
		this.expert = expert;
	}

	public List<String> getContributedQuestions() {
		return contributedQuestions;
	}

	public void setContributedQuestions(List<String> contributedQuestions) {
		this.contributedQuestions = contributedQuestions;
	}

	public List<String> getGeneratedPapers() {
		return generatedPapers;
	}

	public void setGeneratedPapers(List<String> generatedPapers) {
		this.generatedPapers = generatedPapers;
	}

	public List<Integer> getMarks() {
		return marks;
	}

	public void setMarks(List<Integer> marks) {
		this.marks = marks;
	}
	
}
