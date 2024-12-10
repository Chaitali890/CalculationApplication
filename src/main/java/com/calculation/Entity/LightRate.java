package com.calculation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LightRate {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "upper_bound")
	private Integer upperBound;
	
	@Column(name = "lower_bound")
	private Integer lowerBound;
	
	
	@Column(name = "light_rate_in_rupees")
	private Double lightRateInRupees;
	
	@Column(name = "fuel_adjustment_come_in_rupees")
	private Double fuleAdjustmentComeInRupees;
	
	@Column(name = "is_deleted", columnDefinition  = "boolean default 0")
	private Boolean isDeleted = false;
	
	
	
}
