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
import org.springframework.stereotype.Service;

import com.usermanagementapp.bindings.LoginForm;
import com.usermanagementapp.bindings.UnlockAccountForm;
import com.usermanagementapp.bindings.UserForm;
import com.usermanagementapp.entities.CityMaster;
import com.usermanagementapp.entities.CountryMaster;
import com.usermanagementapp.entities.StateMaster;
import com.usermanagementapp.entities.UserMaster;
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
	
	
	private Random random = new Random();
	
	
	

	@Override
	public String checkEmail(String email) {
		
		UserMaster user = userRepo.findByEmail(email);
		
		if(user==null) {
			
			return "Unique";
			
		}
		
		   return "Duplicate";
		
		}

	@Override
	public Map<Integer, String> getCountries() {
		
		    List<CountryMaster> countries = countryRepo.findAll();
		    Map<Integer, String> countryMap = new HashMap();
		    
		    countries.forEach(country -> {
		    	
		    	countryMap.put(country.getCountryId(),	country.getCountryName());
		    	
		    });
			return countryMap;
		    
	   }

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		
		 List<StateMaster> state = stateRepo.findByCountryId(countryId);
		 
		 Map<Integer, String> stateMap = new HashMap(); 
		
		 state.forEach(stateName -> stateMap.put(stateName.getStateId(), stateName.getStateName()));
			
		return stateMap;
		

	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		
		List<CityMaster> cities = cityRepo.findByStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap();
		
		cities.forEach(city -> cityMap.put(city.getCityId(), city.getCityName()));
		
		return cityMap;
	}

	@Override
	public String registerUser(UserForm userForm) {
		
		//copy binding object into entity object
		
	    UserMaster entity = new UserMaster();
		
		BeanUtils.copyProperties(userForm, entity);
		
		//Generate and set Random password
		
		entity.setUserPwd(generateRandomPwd());
		
		
		//set Account status as Locked Status
		
	     entity.setAccStatus("Locked");
		
		 userRepo.save(entity);
		
		 
		 //sent pwd to unlock account
	
		 String to = userForm.getEmail();
		 String subject = "Registration Email";
		 String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		 
		 emailUtils.sendEmail(to, subject, body);
		 
		
          return "User Account Created";
	}

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccForm) {
		
		String email = unlockAccForm.getEmail();
		
		UserMaster user = userRepo.findByEmail(email);
		
		if(user.getUserPwd().equals(unlockAccForm.getTempPwd())){
			
			user.setUserPwd(unlockAccForm.getNewPwd());
			user.setAccStatus("Unlocked");
			userRepo.save(user);
			return "Account Unlocked";
			
		}
		
		return "Invalid Temporary Password";
	}

	@Override
	public String login(LoginForm loginForm) {
		
		UserMaster user = userRepo.findByEmailAndUserPwd(loginForm.getEmail(), loginForm.getPassword());
		
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
		
		UserMaster user = userRepo.findByEmail(email);
		
		if(user==null) {
			
			return "No Account found";
		}
		
		String subject = "Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", user);
		
		emailUtils.sendEmail(email, subject, body);
		
		return "Password send to registered email";
	}
	
	
	private String generateRandomPwd() {
		
		
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder sb = new StringBuilder();
		int pwdLength = 6;
	    
	    for(int i=1; i < pwdLength; i++) {
	    	
	    	int index = random.nextInt(text.length());
	    	
	    	sb.append(text.charAt(index));
	    }
	    
	    
	       return sb.toString();
		
		}
	
	
	public String readEmailBody(String filename, UserMaster user)
	{
		StringBuilder sb = new StringBuilder();
		try(Stream<String> lines = Files.lines(Paths.get(filename))){
			
			lines.forEach(line -> {
				
			    line.replace("${FNAME}", user.getFName());
			    line.replace("${LNAME}", user.getLName());
				line.replace("${TEMP_PWD}", user.getUserPwd());
				line.replace("${EMAIL}", user.getEmail());
				line.replace("${PWD}", user.getUserPwd());
				sb.append(line);
				
			});
			
			}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return sb.toString();
		
		
	}
}
