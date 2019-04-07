package com.example.quartz.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzServiceImpl implements IQuartzService {

	@Autowired
	private Scheduler scheduler;

	@Override
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws SchedulerException {
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
		// 构建job信息
		JobDetail jobDetail = null;
		try {
			jobDetail = JobBuilder.newJob(getClass(jobClassName))
					.withIdentity(jobClassName, jobGroupName).build();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
				.withSchedule(scheduleBuilder).build();
		
		scheduler.scheduleJob(jobDetail, trigger);
	}

	@Override
	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws SchedulerException {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	@Override
	public void deleteJob(String jobClassName, String jobGroupName) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
		//是否需要先暂定触发？
		scheduler.pauseTrigger(triggerKey);
		scheduler.unscheduleJob(triggerKey);
		scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void pauseJob(String jobClassName, String jobGroupName) throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void resumeJob(String jobClassName, String jobGroupName) throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public List<String> getJobGroupNames() throws SchedulerException {
		return scheduler.getJobGroupNames();
	}

	@Override
	public List<JobDetail> getJobDataList(String jobGroupName) throws SchedulerException {
		Set<JobKey>jobkeyset = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroupName));
		List<JobDetail>returnList = new ArrayList<JobDetail>();
		jobkeyset.forEach(jobKey -> {
			try {
				returnList.add(scheduler.getJobDetail(jobKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		});
		return returnList;
	}
	
	private Class<Job> getClass(String classname) throws ClassNotFoundException {
		@SuppressWarnings("unchecked")
		Class<Job> clazz = (Class<Job>) Class.forName(classname);
		return clazz;
	}

}
