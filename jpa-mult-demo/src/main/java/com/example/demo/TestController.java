package com.example.demo;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.test1.Test1Repository;
import com.example.demo.repo.test2.Test2Repository;

@RestController
@RequestMapping(value = "/test", consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
public class TestController {

	@Autowired
	private Test1Repository test1Repository;

	@Autowired
	private Test2Repository test2Repository;

	@GetMapping
	@Transactional(transactionManager="transactionManagerSecondary")
	public void test() {
		User user1 = new User();
		user1.setAge(101);
		user1.setName("guwei1");
		test1Repository.save(user1);

		User user2 = new User();
		user2.setAge(101);
		user2.setName("guwei1");
		test2Repository.save(user2);
		
		System.out.println(10/0);
	}

}
