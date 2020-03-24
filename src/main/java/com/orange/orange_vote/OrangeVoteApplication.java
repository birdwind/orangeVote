package com.orange.orange_vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;


@SpringBootApplication
public class OrangeVoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeVoteApplication.class, args);
	}

}
