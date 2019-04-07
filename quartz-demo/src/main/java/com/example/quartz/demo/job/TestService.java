package com.example.quartz.demo.job;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService{
	
	public void test() {
		System.out.println("testservice1 ========= " + ZonedDateTime.now());
	}

}
