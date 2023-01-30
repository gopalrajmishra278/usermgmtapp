package com.gopal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	
	
	private Logger logger = LoggerFactory.getLogger(WelcomeRestController.class);
    
	@Autowired
	private GreetApiClient greetClient;
	
	@GetMapping("/welcome")
	public String welcomeMsg() {
		
		logger.info("welcomeMsg() execution - start");
		
		String welcomeMsg = "Welcome to AshokIT..!!";
		
		String greetMsg = greetClient.invokeGreetApi();
		
		logger.info("welcomeMsg() execution - end");
		
		return greetMsg + ","+ welcomeMsg;
		
		
	}
}
