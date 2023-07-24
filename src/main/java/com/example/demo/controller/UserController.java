package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InputQuestionDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/qms/save")
	public ResponseEntity<?> registerUser(@RequestBody UserDto new_user) {
		return new ResponseEntity<>(userService.saveUser(new_user), HttpStatus.CREATED);
	}
	
	@GetMapping("/qms/details/{username}")
	public ResponseEntity<?> getUserbyUsername(@PathVariable("username") String username){
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
	@GetMapping("qms/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginDto user){
		return new ResponseEntity<>(userService.checkUser(user), HttpStatus.OK);
	}
	
	@PostMapping("qms/question/save")
	public ResponseEntity<?> getQuestionFromUser(@RequestBody InputQuestionDto new_qsn){
		return new ResponseEntity<>(userService.getQuestion(new_qsn), HttpStatus.CREATED);
	}
}
