package com.cos.pcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class PcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcomApplication.class, args);
	}

}
