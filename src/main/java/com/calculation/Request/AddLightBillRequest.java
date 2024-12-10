package com.calculation.Request;

import lombok.Data;

@Data
public class AddLightBillRequest {

	private Integer id;
	
	private Double rental;
	
	private Double distributionCharges;
	
	private Double electricityTax;
	
	private Double electricitySellTax;
	
	private Double previousBillCredit;
	
	private Double otherCharges;
	
	private Double capacitorPenalty;
	
	private Double adjustment;
	
	private Double refundingOutstanding;
	
	private Double totalRefunding;
	
	
	
}
