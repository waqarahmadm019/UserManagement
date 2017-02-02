package com.usersmanagement.config.logging;

import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Log4jConfigurer;

//@Configuration
//@Profile("production")
public class Log4jConfig {

	@PostConstruct
	public void initLog4j() throws FileNotFoundException {
		Log4jConfigurer.initLogging("classpath:log4j.properties");
	}

}
