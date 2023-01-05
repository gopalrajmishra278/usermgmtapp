package com.usermanagementapp.services;

import java.util.Map;
import com.usermanagementapp.bindings.LoginForm;
import com.usermanagementapp.bindings.UnlockAccountForm;
import com.usermanagementapp.bindings.UserForm;

public interface UserMgmtService {
	
	public String checkEmail(String email);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public String registerUser(UserForm userForm);
	
	public String unlockAccount(UnlockAccountForm accForm);
	
	public String login(LoginForm loginForm);
	
	public String forgotPwd(String email);
	
	
	
}
