package com.usermanagementapp.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.StateMaster;



public interface StateRepo extends JpaRepository<StateMaster, Serializable> {
    
	//select * from states_master where country_id = ?
	public List<StateMaster> findByCountryId(Integer COUNTRY_ID);
}
