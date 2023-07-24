package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.User;


@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findByUsername(String username);
}
