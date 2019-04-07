package com.example.quartz.demo.job;

import java.time.ZonedDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 原生Job
 *
 */
public class TestJob2 implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("testjob2 ====== " + ZonedDateTime.now() );
	}

}
