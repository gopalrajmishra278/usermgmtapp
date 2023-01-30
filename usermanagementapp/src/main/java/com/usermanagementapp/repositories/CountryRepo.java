package com.usermanagementapp.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.CountryMaster;
public interface CountryRepo extends JpaRepository<CountryMaster, Serializable>
{
	
}
