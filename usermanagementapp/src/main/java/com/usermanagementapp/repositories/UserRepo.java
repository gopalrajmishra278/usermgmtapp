package com.usermanagementapp.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;


public interface UserRepo extends JpaRepository<User, Serializable>{
	/*select* from User_Master where email=?*/
	public User findByEmail(String email);
	
	//select * from user_master where email =? and pwd =?
	public User findByEmailAndUserPwd(String email, String Pwd);
	
	
}
