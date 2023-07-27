package com.example.demospringjwttoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {
				"com.example.demospringjwttoken.controller",
				"com.example.demospringjwttoken.service",
				"com.example.demospringjwttoken.config",
				"com.example.demospringjwttoken.helper",
})
@EntityScan("com.example.demospringjwttoken.model")

public class DemospringjwttokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemospringjwttokenApplication.class, args);
		System.out.println("hi");
	}

}
