package com.example.demo.service;

import com.example.demo.dto.InputQuestionDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.RegistrationResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

public interface UserService {
	//user services
	RegistrationResponse saveUser(UserDto new_user);
	User getUserByUsername(String username);
	RegistrationResponse checkUser(LoginDto user);
	
	//question services
	QuestionResponse getQuestion(InputQuestionDto new_qsn);
}
