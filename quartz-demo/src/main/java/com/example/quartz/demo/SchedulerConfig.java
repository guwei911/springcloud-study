package com.example.quartz.demo;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class SchedulerConfig implements SchedulerFactoryBeanCustomizer{
	
	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		 	schedulerFactoryBean.setStartupDelay(2);
	        schedulerFactoryBean.setAutoStartup(true);
	        schedulerFactoryBean.setOverwriteExistingJobs(true);
	}
	
	@Bean("objectMapper")
    public ObjectMapper myMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

}
