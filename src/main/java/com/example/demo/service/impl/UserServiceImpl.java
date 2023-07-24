package com.example.demo.service.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InputQuestionDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.RegistrationResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.QuestionNotValidError;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UsernameAlreadyExistException;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepossitory;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private QuestionRepossitory questionRepossitory;

	@Override
	public RegistrationResponse saveUser(UserDto new_user) {
		
		//checking if email or username already exists
		User ext_user_1 = userRepository.findByEmail(new_user.getEmail());
		User ext_user_2 = userRepository.findByUsername(new_user.getUsername());
		
		if(ext_user_1 != null) {
			throw new EmailAlreadyExistException(new_user.getEmail());
		}
		
		if(ext_user_2 != null) {
			throw new UsernameAlreadyExistException(new_user.getUsername());
		}
		
		//saving the new user
		User user = new User();
		user.setEmail(new_user.getEmail());
		user.setFirstName(new_user.getFirstName());
		user.setLastName(new_user.getLastName());
		user.setUsername(new_user.getUsername());
		user.setPassword(this.passwordEncoder.encode(new_user.getPassword()));

		userRepository.save(user);
		
		//sending the response
		RegistrationResponse response = new RegistrationResponse("Successfully registered", true);
		
		return response;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public RegistrationResponse checkUser(LoginDto user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if(existingUser == null) {
			throw new ResourceNotFoundException("Username");
		}
		else {
			
			Boolean isPwdRight = passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
			if(isPwdRight) {
				return new RegistrationResponse("Login Successful",true);
			}
			else {
				throw new ResourceNotFoundException("Password");
			}
		}
	}

	@Override
	public QuestionResponse getQuestion(InputQuestionDto new_qsn){
		try {
			Map<String, Object> inputData = new HashMap<>();
	        inputData.put("question", new_qsn.getQuestion());
	        inputData.put("topic", new_qsn.getTopic());
	        String inputDataJson = new ObjectMapper().writeValueAsString(inputData);

	        // Run the Python script as a subprocess
	        ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\Subhadeep_Sarkar\\Desktop\\Hackethon\\questionbank_2_py.py");
	        processBuilder.redirectErrorStream(true);
	        Process process = processBuilder.start();

	        // Get the input stream for writing data to Python stdin
	        OutputStream outputStream = process.getOutputStream();
	        outputStream.write(inputDataJson.getBytes());
	        outputStream.close();

	        // Get the output stream for reading data from Python stdout
	        InputStream inputStream = process.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

	        // Read the output data from Python and parse it as JSON
	        StringBuilder outputDataJson = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            outputDataJson.append(line);
	        }
	        reader.close();

	        // Parse the JSON response from Python
	        Map<String, Object> outputData = new ObjectMapper().readValue(outputDataJson.toString(), Map.class);

	        boolean sim_qsn = (boolean) outputData.get("similar_questions");
	        boolean sim_topic = (boolean) outputData.get("same_topic");
	        boolean invalid_topic = (boolean) outputData.get("invalid_topic");
	        
	        if(!sim_qsn && !sim_topic && !invalid_topic) {
	        	Question question = new Question();
	        	question.setUsername(new_qsn.getUsername());
	        	question.setQuestion(new_qsn.getQuestion());
	        	question.setSubject(new_qsn.getSubject());
	        	question.setTopic(new_qsn.getTopic());
	        	question.setMarks((int)(outputData.get("marks")));
	        	question.setWeightage((int)(outputData.get("weightage")));;
	        		
	        	questionRepossitory.save(question);
	        	
	        	return new QuestionResponse("Question added successfully",true);
	        }
	        else if(sim_qsn) {
	        	return new QuestionResponse("similar question already exists with the name : "+outputData.get("similar_question_name"),false);
	        	
	        }
	        else if(invalid_topic) {
	        	return new QuestionResponse("The topic of the question seems wrong with the name : "+new_qsn.getTopic(),false);
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		//try
		
		return new QuestionResponse("A strange error occured", false);
		
	}

}
