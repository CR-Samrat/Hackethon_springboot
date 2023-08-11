package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.GeneratedPaper;

public interface GeneratedPaperRepository extends JpaRepository<GeneratedPaper, Long>{
	List<GeneratedPaper> findByEmail(String email);
	
	@Query(value = "SELECT * FROM qsn_papers_db q WHERE q.email=:email AND q.title=:title", nativeQuery = true)
	List<GeneratedPaper> findQuestions(String email, String title);

	List<GeneratedPaper> findByTitle(String title);

}
