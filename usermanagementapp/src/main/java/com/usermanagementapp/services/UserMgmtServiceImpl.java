package com.usermanagementapp.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.usermanagementapp.bindings.LoginForm;
import com.usermanagementapp.bindings.UnlockAccountForm;
import com.usermanagementapp.bindings.UserForm;
import com.usermanagementapp.entities.City;
import com.usermanagementapp.entities.Country;
import com.usermanagementapp.entities.State;
import com.usermanagementapp.repositories.CityRepo;
import com.usermanagementapp.repositories.CountryRepo;
import com.usermanagementapp.repositories.StateRepo;
import com.usermanagementapp.repositories.UserRepo;
import com.usermanagementapp.util.EmailUtils;


@Service
public class UserMgmtServiceImpl implements UserMgmtService{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	StateRepo stateRepo;
	
	@Autowired
	CountryRepo countryRepo;
	
	@Autowired
    CityRepo cityRepo;
	
	@Autowired	
	EmailUtils emailUtils;
	
	
	

	@Override
	public String checkEmail(String email) {
		
		User user = userRepo.findByEmail(email);
		
		if(user==null) {
			
			return "Unique";
			
		}
		
		   return "Duplicate";
		
		}

	@Override
	public Map<Integer, String> getCountries() {
		
		    List<Country> countries = countryRepo.findAll();
		    Map<Integer, String> countryMap = new HashMap();
		    
		    countries.forEach(country -> {
		    	
		    	countryMap.put(country.getCountryId(),	country.getCountryName());
		    	
		    });
			return countryMap;
		    
	   }

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		
		 List<State> state = stateRepo.findByCountryId(countryId);
		 
		 Map<Integer, String> stateMap = new HashMap(); 
		
		 state.forEach(stateName -> {
			 
			 
			 stateMap.put(stateName.getStateId(), stateName.getStateName()); 
			 });
		return stateMap;
		

	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		
		List<City> cities = cityRepo.findByStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap();
		
		cities.forEach(city ->{
			
			cityMap.put(city.getCityId(), city.getCityName());
		});
		
		return cityMap;
	}

	@Override
	public String registerUser(UserForm userForm) {
		
		//copy binding object into entity object
		
		User entity = new User();
		
		BeanUtils.copyProperties(userForm, entity);
		
		//Generate and set password
		
		entity.setUserPwd(generateRandomPwd());
		
		
		//set Account status as Locked Status
		
	     entity.setAccStatus("Locked");
		
		 userRepo.save(entity);
		
		//sent  Email to Unlock account
		 
		 emailUtils.sendEmail(to, subject, body);
		 
		 String to = userForm.getEmail();
		 String subject = "Registration Email";
		 String body = "";
		 
		 emailUtils.sentEmail(to, subject, body);
		 
		
          return "User Account Created";
	}

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccForm) {
		
		
		String email = unlockAccForm.getEmail();
		
		User user = userRepo.findByEmail(email);
		
		if(user.getUserPwd().equals(unlockAccForm.getTempPwd()){
			
			user.setUserPwd(unlockAccForm.setNewPwd());
			user.setAccStatus("Unlocked");
			userRepo.save(user);
			return "Account Unlocked";
			
		}
		
		return "Invalid Temporary Password";
	}

	@Override
	public String login(LoginForm loginForm) {
		
		User user = userRepo.findByEmailAndUserPwd(loginForm.getEmail(), loginForm.getPassword());
		
		if(user==null) {
			
			return "Invalid Credentials";
		}
		
		if(user.getAccStatus().equals("Locked")) {
			
			return "Account Locked";
		}
		
		
		return "SUCCESS";
	}

	@Override
	public String forgotPwd(String email) {
		
		User user = userRepo.findByEmail(email);
		
		if(user==null) {
			
			return "No Account found";
		}
		
		
		
		return null;
	}
	
	
	private String generateRandomPwd() {
		
		
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder sb = new StringBuilder();
		
	    Random random = new Random();
	    
	    int pwdLength = 6;
	    
	    for(int i=1; i < pwdLength; i++) {
	    	
	    	int index = random.nextInt(text.length());
	    	
	    	sb.append(text.charAt(index));
	    }
	    
	    
	       return sb.toString();
		
		}
	
	
	public String readEmailBody(String filename, User user)
	{
		try(Stream<String> lines = Files.lines(Paths.get(filename))){
			
			lines.forEach(line -> {
				
				line.replace("${firstName}", user.getFName());
				line.replace("${firstName}", user.getFirstName());
				
				
			})
			
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
		
		
	}
}
