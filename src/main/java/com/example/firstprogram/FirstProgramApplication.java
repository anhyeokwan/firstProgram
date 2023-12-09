package com.example.firstprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FirstProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProgramApplication.class, args);
	}

}
