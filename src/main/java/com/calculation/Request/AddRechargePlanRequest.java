package com.calculation.Request;

import lombok.Data;

@Data
public class AddRechargePlanRequest {

	private Integer id;
	
	private String plan;
	
	private Integer year;

	private Double price;
}
