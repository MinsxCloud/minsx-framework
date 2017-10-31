package com.minsx.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * ApplicationStarter Created by Joker on 2017/8/29.
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.minsx")
@ComponentScan(basePackages = "com.minsx")
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
