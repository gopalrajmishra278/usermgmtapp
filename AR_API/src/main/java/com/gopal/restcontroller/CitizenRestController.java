package com.gopal.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitizenRestController {
	
	private Logger logger = LoggerFactory.getLogger(CitizenRestController.class);
	
	private  ARApiClient arApiClient;
	
	@GetMapping("/ssn")
	public String arMsg(@PathVariable Integer ssn) {
		
		String uri = "http://ssawebapi-env.eba-k88bsahp.ap-south-1.elasticbeanstalk.com/ssn/{ssn}";
		
		
		String arMsg = "Success";
		
		String arSMsg = arApiClient.invokeARApi();
		
		return arMsg + ", "+ arMsg;
		
		
	}
	
	
	

}
