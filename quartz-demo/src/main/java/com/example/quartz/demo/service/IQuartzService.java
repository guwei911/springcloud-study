package com.example.quartz.demo.service;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;

/**
 * 只是支持了cron任务，是否要支持simple类型、dailytime类型等？感觉必要性不大
 *
 */
public interface IQuartzService {

	/**
	 * 创建cron任务
	 * 
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws SchedulerException;

	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws SchedulerException;

	public void deleteJob(String jobClassName, String jobGroupName) throws SchedulerException;

	public void pauseJob(String jobClassName, String jobGroupName) throws SchedulerException;

	public void resumeJob(String jobClassName, String jobGroupName) throws SchedulerException;

	public List<String> getJobGroupNames() throws SchedulerException;

	public List<JobDetail> getJobDataList(String jobGroupName) throws SchedulerException;

//	public Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher) throws SchedulerException;
//	
//	public Trigger getTriggerDetail(String group, String name) throws SchedulerException;

	// public JobDetail getJobDetail(JobKey jobKey) throws SchedulerException;

}
