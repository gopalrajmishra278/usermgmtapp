package com.usermanagementapp.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="USER_MASTER")
public class UserMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String fName;
	private String lName;
	private String email;
	private String phno;
	private LocalDate dob;
	private String gender;
	private Integer country;
	private Integer state;
	private Integer city;
	private String userPwd;
	private String accStatus;
	
}
