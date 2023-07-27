package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ContributedQuestionsDto;
import com.example.demo.dto.ExamSubmitRequestDto;
import com.example.demo.dto.ExpertQuestionDto;
import com.example.demo.dto.GenerateRequestDto;
import com.example.demo.dto.GenerateResponseDto;
import com.example.demo.dto.InputQuestionDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.RegistrationResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Expert;
import com.example.demo.model.Question;
import com.example.demo.model.User;

public interface UserService {
	//user services
	RegistrationResponse saveUser(UserDto new_user);
	User getUserByEmail(String email);
	RegistrationResponse checkUser(LoginDto user);
	List<ContributedQuestionsDto> getContributedQuestions(String email);
	
	//question services
	QuestionResponse getQuestion(InputQuestionDto new_qsn);
	List<Question> generateQuestions(GenerateRequestDto qsn_patern);
	
	//Expert services
	Expert addExpertQuestion(Expert new_qsn);
	List<ExpertQuestionDto> generatExpertQuestion(String subject);
	QuestionResponse expertExamResult(ExamSubmitRequestDto new_paper);
}
