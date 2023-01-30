package com.gopal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClien
@EnableFeignClients
public class WELCOMEAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(WELCOMEAPIApplication.class, args);
	}

}
