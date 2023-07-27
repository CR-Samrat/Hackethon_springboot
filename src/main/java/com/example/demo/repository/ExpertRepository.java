package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.ExpertQuestionDto;
import com.example.demo.model.Expert;

public interface ExpertRepository extends JpaRepository<Expert, Long>{
	List<Expert> findBySubject(String subject);
}
