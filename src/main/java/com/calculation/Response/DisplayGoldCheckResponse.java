package com.calculation.Response;

import lombok.Data;

@Data
public class DisplayGoldCheckResponse {

	private Integer gram;
	
	private String month;
	
	private Integer year;
	
	private double price;
	
	private double pricebasedOnRate;

	private double enterGram;
	
	private double enterMilligram;
}
