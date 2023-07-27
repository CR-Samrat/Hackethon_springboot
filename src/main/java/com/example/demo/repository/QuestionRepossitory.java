package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Question;

public interface QuestionRepossitory extends JpaRepository<Question, Long>{
	List<Question> findBySubjectAndTopicIn(String subject, List<String> topic);
	List<Question> findByEmail(String email);
}
