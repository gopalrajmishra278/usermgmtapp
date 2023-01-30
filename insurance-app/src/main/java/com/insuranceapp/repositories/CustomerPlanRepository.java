package com.insuranceapp.repositories;


import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insuranceapp.entities.CustomerPlan;

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan, Serializable>{
	
	@Query("select distinct(planName) from CustomerPlan")
	public List<String> getPlanNames();
	
	@Query("select distinct(planStatus) from CustomerPlan")
	public List<String> getPlanStatues();
	
	
	

}
