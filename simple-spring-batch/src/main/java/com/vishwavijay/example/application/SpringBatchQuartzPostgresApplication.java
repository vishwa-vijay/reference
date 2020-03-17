package com.vishwavijay.example.application;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchQuartzPostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchQuartzPostgresApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner1() {
		return (args)->{
			System.out.println("Hello...................");
		};
	}
	

}
