package com.insuranceapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CUSTOMER_DETAILS")
public class CustomerPlan {
	
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer customerId;
     private String planName;
     private String planStatus;
     private String customerName;
	 private String customerEmail;
	 private String gender;
     private Long customerMobNum;
	 private Long ssn;
	
	}
