package com.example.bico.appraiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BicoAppraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BicoAppraiserApplication.class, args);
	}

}
