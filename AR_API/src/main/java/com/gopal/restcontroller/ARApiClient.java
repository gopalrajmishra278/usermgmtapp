package com.gopal.restcontroller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AR-API")
public interface ARApiClient {
	
	@GetMapping("/ar")
	public String invokeARApi();
	
	
}
