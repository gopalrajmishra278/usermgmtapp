package com.usermanagementapp.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
	
	private String firstName;
	private String lastName;
	private String email;
    private Long phno;
    private LocalDate dob;
    private String male;
    private Integer country;
    private Integer sate;
    private Integer city;
    
    
}
