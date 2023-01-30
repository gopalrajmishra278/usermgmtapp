package com.gopal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GREETAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(GREETAPIApplication.class, args);
	}
	
	@GetMapping("/welcome")
	public String welcomeApi() {
		
		return "Welcome To GopalIT";
		
	}

}
