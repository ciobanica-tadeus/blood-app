package com.example.bloodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BloodAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodAppApplication.class, args);
	}

}
