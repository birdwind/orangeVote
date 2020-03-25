package com.orange.orange_vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@ComponentScan(lazyInit = true, value = {"com.orange.orange_vote.*"})
@EnableJpaRepositories(bootstrapMode = BootstrapMode.LAZY, value = {"com.orange.orange_vote.repo.dao"})
@EntityScan({"com.orange.orange_vote.*"})
@SpringBootApplication
public class OrangeVoteApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OrangeVoteApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OrangeVoteApplication.class, args);
	}

}
