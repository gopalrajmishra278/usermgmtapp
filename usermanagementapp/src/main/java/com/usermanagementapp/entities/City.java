package com.usermanagementapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity(name="CITY_MASTER")
public class City {
	@Id
	private int cityId;
	private String cityName;
	

}
