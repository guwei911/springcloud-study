package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {
	
	@Bean
	@Primary
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix="spring.primary.datasource")
	public DataSource primaryDatasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Qualifier("secondaryDataSource")
	@ConfigurationProperties(prefix="spring.secondary.datasource")
	public DataSource secondaryDatasource() {
		return DataSourceBuilder.create().build();
	}

}
