package com.usermanagementapp.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entities.Country;

public interface CountryRepo extends JpaRepository<Country, Serializable>
{
	
}
