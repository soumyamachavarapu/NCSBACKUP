package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestaurentAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurentAuthApplication.class, args);
	}

}
