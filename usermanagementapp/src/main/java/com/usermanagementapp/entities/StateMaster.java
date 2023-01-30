package com.usermanagementapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="STATE_MASTER")
public class StateMaster {
	@Id
	@GeneratedValue
	private Integer stateId;
	private Integer countryId;
	private String stateName;

}
