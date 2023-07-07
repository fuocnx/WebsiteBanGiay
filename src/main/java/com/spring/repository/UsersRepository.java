package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByUsernameAndPassword(String username, String password);

}
