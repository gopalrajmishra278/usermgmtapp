package com.usermanagementapp.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.State;

public interface StateRepo extends JpaRepository<State, Serializable> {
    
	//select * from states_master where country_id = ?
	public List<State> findByCountryId(Integer COUNTRY_ID);
}
