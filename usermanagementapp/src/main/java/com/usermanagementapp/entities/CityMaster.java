package com.usermanagementapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity(name="CITY_MASTER")
public class CityMaster {
	@Id
	@GeneratedValue
	private Integer cityId;
	private Integer stateId;
	private String cityName;
	

}
