package com.gopal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class ArApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArApiApplication.class, args);
	}

}
