package com.calculation.Response;

import lombok.Data;

@Data
public class DisplayChangePlanResponse {

	private Integer id;
	
	private String plan;
	
	private Integer year;
	
	private Integer newYear;
	
	private Double oldprice;
	
	private Double newPrice;
}
