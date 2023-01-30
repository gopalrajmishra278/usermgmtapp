package com.usermanagementapp.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.UserMaster;





public interface UserRepo extends JpaRepository<UserMaster, Serializable>{
	//*select* from User_Master where email=?
	public UserMaster findByEmail(String email);
	
	//select * from user_master where email =? and pwd =?
	public UserMaster findByEmailAndUserPwd(String email, String pwd);
	
	
}
