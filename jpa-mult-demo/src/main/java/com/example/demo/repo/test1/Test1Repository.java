package com.example.demo.repo.test1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface Test1Repository extends JpaRepository<User, Long> {

}
