package com.example.demo.service.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ContributedQuestionsDto;
import com.example.demo.dto.ExamSubmitRequestDto;
import com.example.demo.dto.ExpertQuestionDto;
import com.example.demo.dto.GenerateRequestDto;
import com.example.demo.dto.GenerateResponseDto;
import com.example.demo.dto.GeneratedPaperRequest;
import com.example.demo.dto.InputQuestionDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.RegistrationResponse;
import com.example.demo.dto.UserDetailsDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.QuestionNotValidError;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UsernameAlreadyExistException;
import com.example.demo.model.Expert;
import com.example.demo.model.GeneratedPaper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.ExpertRepository;
import com.example.demo.repository.GeneratedPaperRepository;
import com.example.demo.repository.QuestionRepossitory;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private QuestionRepossitory questionRepossitory;
	
	@Autowired
	private ExpertRepository expertRepository;
	
	@Autowired
	private GeneratedPaperRepository generatedPaperRepository;

	@Override
	public RegistrationResponse saveUser(UserDto new_user) {
		
		//checking if email or username already exists
		User ext_user_1 = userRepository.findByEmail(new_user.getEmail());
		User ext_user_2 = userRepository.findByUsername(new_user.getUsername());
		
		if(ext_user_1 != null) {
			throw new EmailAlreadyExistException(new_user.getEmail());
		}
		
		if(ext_user_2 != null) {
			throw new UsernameAlreadyExistException(new_user.getUsername(), "Username");
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
	public UserDetailsDto getUserByEmail(String email) {
		User extUser = userRepository.findByEmail(email);
		
		UserDetailsDto new_user = new UserDetailsDto();
		
		new_user.setUsername(extUser.getUsername());
		new_user.setFirstName(extUser.getFirstName());
		new_user.setLastName(extUser.getLastName());
		new_user.setEmail(extUser.getEmail());
		new_user.setExpert(extUser.getExpert());
		
		List<Question> qsn_details = questionRepossitory.findByEmail(email);
		List<GeneratedPaper> paper_details = generatedPaperRepository.findByEmail(email);
		List<String> qsnList = new ArrayList<>();
		List<String> topicList = new ArrayList<>();
		List<Integer> marksList = new ArrayList<>();
		List<String> paperTitles = new ArrayList<>();
		
		for(Question each_qsn: qsn_details) {
			qsnList.add(each_qsn.getQuestion());
		}
		
		for(GeneratedPaper paper : paper_details) {
			topicList.add(paper.getTopic());
			marksList.add(paper.getMarks());
			paperTitles.add(paper.getTitle());
		}
		
		new_user.setContributedQuestions(qsnList);
		new_user.setGeneratedPapers(topicList);
		new_user.setPaperTitles(paperTitles);
		new_user.setMarks(marksList);
		
		return new_user;
	}

	@Override
	public RegistrationResponse checkUser(LoginDto user) {
		User existingUser = userRepository.findByEmail(user.getEmail());
		
		if(existingUser == null) {
			throw new ResourceNotFoundException("Email");
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
	        	question.setEmail(new_qsn.getEmail());
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

	@Override
	public List<Question> generateQuestions(GenerateRequestDto qsn_patern) {
		List<GeneratedPaper> extPapers = generatedPaperRepository.findQuestions(qsn_patern.getEmail(), qsn_patern.getTitle());
		
		if(extPapers.size()==0) {
			List<Question> qsn_list = questionRepossitory.findBySubjectAndTopicIn(qsn_patern.getSubject(), qsn_patern.getTopic()); 
			
			List<Question> mark_one_list = new ArrayList<>();
			List<Question> mark_two_list = new ArrayList<>();
			List<Question> mark_three_list = new ArrayList<>();
			List<Question> mark_four_list = new ArrayList<>();
			List<Question> mark_five_list = new ArrayList<>();
			List<Question> final_paper = new ArrayList<>();
			
			for(Question each_qsn : qsn_list) {
				if(each_qsn.getMarks()==1) {
					mark_one_list.add(each_qsn);
				}
				else if(each_qsn.getMarks()==2) {
					mark_two_list.add(each_qsn);
				}
				else if(each_qsn.getMarks()==3) {
					mark_three_list.add(each_qsn);
				}
				else if(each_qsn.getMarks()==4) {
					mark_four_list.add(each_qsn);
				}
				else if(each_qsn.getMarks()==5) {
					mark_five_list.add(each_qsn);
				}
			}
			
			// Shuffle the lists
		    Collections.shuffle(mark_two_list);
		    Collections.shuffle(mark_three_list);
		    Collections.shuffle(mark_five_list);
		    Collections.shuffle(mark_one_list);
		    Collections.shuffle(mark_four_list);
			
			//for marks 50
			if(qsn_patern.getFull_marks()==50) {
				for(int i = 0; i<5; i++) {
					final_paper.add(mark_two_list.get(i));
				}
				for(int i = 0; i<5; i++) {
					final_paper.add(mark_three_list.get(i));
				}
				for(int i = 0; i<5; i++) {
					final_paper.add(mark_five_list.get(i));
				}
			}
			else if(qsn_patern.getFull_marks()==30) {
				for(int i = 0; i<5; i++) {
					final_paper.add(mark_one_list.get(i));
				}
				for(int i = 0; i<10; i++) {
					final_paper.add(mark_two_list.get(i));
				}
				for(int i = 0; i<3; i++) {
					final_paper.add(mark_five_list.get(i));
				}
			}
			else if(qsn_patern.getFull_marks()==70) {
				for(int i = 0; i<10; i++) {
					final_paper.add(mark_two_list.get(i));
				}
				for(int i = 0; i<5; i++) {
					final_paper.add(mark_four_list.get(i));
				}
				for(int i = 0; i<6; i++) {
					final_paper.add(mark_five_list.get(i));
				}
			}
			else if(qsn_patern.getFull_marks()==100) {
				for(int i = 0; i<10; i++) {
					final_paper.add(mark_one_list.get(i)); //10
				}
				for(int i = 0; i<10; i++) {
					final_paper.add(mark_two_list.get(i)); //20
				}
				
				for(int i = 0; i<10; i++) {
					final_paper.add(mark_three_list.get(i)); //30
				}
				
				for(int i = 0; i<10; i++) {
					final_paper.add(mark_five_list.get(i)); //50
				}
			}
			
			//adding data in the qsn_paper_db table
			String topic = "";
			for(String str: qsn_patern.getTopic()) {
				topic += str+", ";
			}
			
			topic = topic.substring(0, topic.length()-2);
			
			GeneratedPaper generatedPaper = new GeneratedPaper();
			generatedPaper.setEmail(qsn_patern.getEmail());
			generatedPaper.setMarks(qsn_patern.getFull_marks());
			generatedPaper.setSubject(qsn_patern.getSubject());
			generatedPaper.setTitle(qsn_patern.getTitle());
			generatedPaper.setTopic(topic);
			generatedPaper.setQuestions(final_paper);
			
			generatedPaperRepository.save(generatedPaper);
			
			
			return final_paper;
		}else {
			throw new UsernameAlreadyExistException(qsn_patern.getTitle(), "Title");
		}
		
		
	}

	@Override
	public Expert addExpertQuestion(Expert new_qsn) {
		return expertRepository.save(new_qsn);
	}

	@Override
	public List<ExpertQuestionDto> generatExpertQuestion(String subject) {
		System.out.println(subject);
		List<Expert> qsn_list = expertRepository.findBySubject(subject);
		
		Collections.shuffle(qsn_list);
		
		List<ExpertQuestionDto> final_paper = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			ExpertQuestionDto new_qsn = new ExpertQuestionDto();
			new_qsn.setQuestion(qsn_list.get(i).getQuestion());
			new_qsn.setOption_a(qsn_list.get(i).getOption_a());
			new_qsn.setOption_b(qsn_list.get(i).getOption_b());
			new_qsn.setOption_c(qsn_list.get(i).getOption_c());
			new_qsn.setOption_d(qsn_list.get(i).getOption_d());
			new_qsn.setAnswer(qsn_list.get(i).getAnswer());
			new_qsn.setSubject(qsn_list.get(i).getSubject());
			
			final_paper.add(new_qsn);
		}
		
		return final_paper;
	}


	@Override
	public QuestionResponse expertExamResult(ExamSubmitRequestDto new_paper) {
		if(new_paper.getMarks()>7) {
			User extUser = userRepository.findByEmail(new_paper.getEmail());
			extUser.setExpert(new_paper.getSubject());
			userRepository.save(extUser);
			
			return new QuestionResponse("Exam passed successfully", true);
		}
		
		return new QuestionResponse("Exam not cleared.. you can try again anytime.", false);
	}

	@Override
	public List<ContributedQuestionsDto> getContributedQuestions(String email) {
		List<Question> qsn_list = questionRepossitory.findByEmail(email);
		List<ContributedQuestionsDto> finaList = new ArrayList<>();
		
		for(Question each_qsn: qsn_list) {
			ContributedQuestionsDto new_qsn = new ContributedQuestionsDto();
			new_qsn.setMarks(each_qsn.getMarks());
			new_qsn.setQuestion(each_qsn.getQuestion());
			new_qsn.setTopic(each_qsn.getTopic());
			
			finaList.add(new_qsn);
		}
		
		return finaList;
	}

	@Override
	public List<QuestionDto> getGeneratedPapers(GeneratedPaperRequest paper) {
		List<GeneratedPaper> ext_paper = generatedPaperRepository.findQuestions(paper.getEmail(), paper.getTitle());
//		List<GeneratedPaper> ext_paper = generatedPaperRepository.findByTitle(paper.getTitle());
		List<Question> extQuestions = ext_paper.get(0).getQuestions();
		List<QuestionDto> final_paper = new ArrayList<>();
		
		for(Question q: extQuestions) {
			QuestionDto new_qsn = new QuestionDto();
			new_qsn.setId(q.getId());
			new_qsn.setQuestion(q.getQuestion());
			new_qsn.setMarks(q.getMarks());
			new_qsn.setFull_marks(ext_paper.get(0).getMarks());
			
			final_paper.add(new_qsn);
		}
		
		return final_paper;
	}

	

}
