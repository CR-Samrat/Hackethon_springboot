package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.GeneratedPaper;

public interface GeneratedPaperRepository extends JpaRepository<GeneratedPaper, Long>{
	List<GeneratedPaper> findByEmail(String email);
}
