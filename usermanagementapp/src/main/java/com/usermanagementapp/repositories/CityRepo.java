package com.usermanagementapp.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.City;


public interface CityRepo extends JpaRepository<City, Serializable>{
	
	//select * from CITY_MASTER where STATE_ID = ?
	public List<City> findByStateId(Integer stateId);
	
	

}
