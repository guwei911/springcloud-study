package com.example.quartz.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quartz.demo.service.IQuartzService;

@RestController
@RequestMapping(value = "/job", consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
public class QuratzController {

	@Autowired
	private IQuartzService quartzService;

	@PostMapping(value = "/add")
	public void addJob(@RequestParam("jobClassName") String jobClassName,
			@RequestParam("jobGroupName") String jobGroupName, @RequestParam("cronExpression") String cronExpression)
			throws SchedulerException {
		quartzService.addJob(jobClassName, jobGroupName, cronExpression);
	}

	@PutMapping(value = "/update")
	public void updateJob(@RequestParam("jobClassName") String jobClassName,
			@RequestParam("jobGroupName") String jobGroupName, @RequestParam("cronExpression") String cronExpression)
			throws SchedulerException {
		quartzService.updateJob(jobClassName, jobGroupName, cronExpression);
	}

	@DeleteMapping
	public void deleteJob(@RequestParam("jobClassName") String jobClassName,
			@RequestParam("jobGroupName") String jobGroupName) throws SchedulerException {
		quartzService.deleteJob(jobClassName, jobGroupName);
	}

	@PostMapping(value = "/pause")
	public void pauseJob(@RequestParam("jobClassName") String jobClassName,
			@RequestParam("jobGroupName") String jobGroupName) throws SchedulerException {
		quartzService.pauseJob(jobClassName, jobGroupName);
	}

	@PostMapping(value = "/resume")
	public void resumeJob(@RequestParam("jobClassName") String jobClassName,
			@RequestParam("jobGroupName") String jobGroupName) throws SchedulerException {
		quartzService.resumeJob(jobClassName, jobGroupName);
	}

	@GetMapping(value = "/groups")
	public List<String> getJobGroupNames() throws SchedulerException {
		return quartzService.getJobGroupNames();
	}

	@GetMapping(value = "/jobs")
	public List<JobDetail> getJobDataList(@RequestParam("jobGroupName") String jobGroupName) throws SchedulerException {
		return quartzService.getJobDataList(jobGroupName);
	}

}
