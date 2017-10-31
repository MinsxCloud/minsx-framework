package com.minsx.starter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages="com.minsx")
@EnableJpaRepositories(basePackages="com.minsx")
public class JpaConfig {

}
