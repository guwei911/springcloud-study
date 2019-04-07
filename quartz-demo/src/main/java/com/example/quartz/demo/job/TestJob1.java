package com.example.quartz.demo.job;

import java.time.ZonedDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 支持依赖注入
 * 
 */
public class TestJob1 extends QuartzJobBean {
	
	@Autowired
	private ITestService testService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("testjob1 ====== " + ZonedDateTime.now() );
		testService.test();
	}

}
