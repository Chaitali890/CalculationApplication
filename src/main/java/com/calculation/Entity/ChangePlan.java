package com.calculation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ChangePlan {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "newYear")
	private Integer newYear;
	
	@Column(name = "newPrice")
	private Double newPrice;
	
	@Column(name = "recharge_plan_id")
	private Integer rechargePlanId;
	
}
