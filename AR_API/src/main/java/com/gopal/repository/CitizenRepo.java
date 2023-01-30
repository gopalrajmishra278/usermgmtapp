package com.gopal.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gopal.entity.CitizenMaster;

public interface CitizenRepo extends JpaRepository<CitizenMaster, Long> {
	
	
	

}
