package com.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	public User findByEmail(String email);
}