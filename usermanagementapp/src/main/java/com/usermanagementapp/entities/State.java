package com.usermanagementapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="STATE_MASTER")
public class State {
	@Id
	private Integer stateId;
	private String stateName;

}
