package com.apistarwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiStarWarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiStarWarsApplication.class, args);
	}

}