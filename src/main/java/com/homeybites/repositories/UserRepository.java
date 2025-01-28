package com.homeybites.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeybites.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmailId(String emailId);
	
	
	boolean existsByEmailId(String username);
}
