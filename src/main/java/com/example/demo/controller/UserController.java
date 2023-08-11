package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExamSubmitRequestDto;
import com.example.demo.dto.GenerateRequestDto;
import com.example.demo.dto.GeneratedPaperRequest;
import com.example.demo.dto.InputQuestionDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Expert;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000/")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/qms/save")
	public ResponseEntity<?> registerUser(@RequestBody UserDto new_user) {
		return new ResponseEntity<>(userService.saveUser(new_user), HttpStatus.CREATED);
	}
	
	@GetMapping("/qms/details/{email}")
	public ResponseEntity<?> getUserbyEmail(@PathVariable("email") String email){
		return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping("qms/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginDto user){
		return new ResponseEntity<>(userService.checkUser(user), HttpStatus.OK);
	}
	
	@PostMapping("qms/question/save")
	public ResponseEntity<?> getQuestionFromUser(@RequestBody InputQuestionDto new_qsn){
		return new ResponseEntity<>(userService.getQuestion(new_qsn), HttpStatus.CREATED);
	}
	
	@PostMapping("/qms/generate")
	public ResponseEntity<?> getGeneratedQuestion(@RequestBody GenerateRequestDto generateRequest){
		return new ResponseEntity<>(userService.generateQuestions(generateRequest), HttpStatus.OK);
	}
	
	@PostMapping("/qms/expert/qsn/save")
	public ResponseEntity<?> addExpertQuestion(@RequestBody Expert new_qsn){
		return new ResponseEntity<>(userService.addExpertQuestion(new_qsn), HttpStatus.OK);
	}
	
	@GetMapping("/qms/expert/qsn/generate/{subject}")
	public ResponseEntity<?> generateExpertQuestion(@PathVariable("subject") String subject){
		return new ResponseEntity<>(userService.generatExpertQuestion(subject), HttpStatus.OK);
	}
	
	@PostMapping("qms/expert/qsn/result")
	public ResponseEntity<?> getExpertExamResult(@RequestBody ExamSubmitRequestDto new_paper){
		return new ResponseEntity<>(userService.expertExamResult(new_paper), HttpStatus.OK);
	}
	
	@GetMapping("qms/contributed/{email}")
	public ResponseEntity<?> getContributedQuestion(@PathVariable String email){
		return new ResponseEntity<>(userService.getContributedQuestions(email), HttpStatus.OK);
	}
	
	@PostMapping("qms/get/papers")
	public ResponseEntity<?> getGeneratedPapers(@RequestBody GeneratedPaperRequest paper){
		return new ResponseEntity<>(userService.getGeneratedPapers(paper), HttpStatus.OK);
	}
}
