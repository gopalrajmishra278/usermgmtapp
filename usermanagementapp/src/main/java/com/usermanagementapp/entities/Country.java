package com.usermanagementapp.entities;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY_MASTER")
public class Country {
	@Id
	private int countryId;
	private String countryName;
	

}
