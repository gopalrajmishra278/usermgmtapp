package com.usermanagementapp.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.CityMaster;


public interface CityRepo extends JpaRepository<CityMaster, Serializable>{
	
	//select * from CITY_MASTER where STATE_ID = ?
	public List<CityMaster> findByStateId(Integer stateId);
	
	

}
