package com.gopal.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table
public class CitizenMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long appId;
	private String name;
	private String email;
	private Long mobileNo;
	private String Gender;
	private Date dob;
	private Long SSN;
	
}
