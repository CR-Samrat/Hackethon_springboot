package com.example.demo.exception;

public class QuestionNotValidError extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error_name;
	private Object error_Value;
	
	public QuestionNotValidError(String error_name, Object error_Value) {
		super(String.format("Question can't be added as %s with the name : '%s'",error_name,error_Value));
		this.error_name = error_name;
		this.error_Value = error_Value;
	}

	public String getError_name() {
		return error_name;
	}

	public void setError_name(String error_name) {
		this.error_name = error_name;
	}

	public Object getError_Value() {
		return error_Value;
	}

	public void setError_Value(Object error_Value) {
		this.error_Value = error_Value;
	}
	
}
