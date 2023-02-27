package com.masai.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.blogApp.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
}
