package com.example.demo.repo.test2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface Test2Repository extends JpaRepository<User, Long> {

}