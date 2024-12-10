package com.calculation.Request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddGoldCheckRequest {

	private Integer id;

	private Integer gram;
	
	private Integer miligram;

	private double price;

	private String month;

	private Integer year;

}
