package com.calculation.Response;

import lombok.Data;

@Data
public class DisplayElectricityBillResponse {

	private Double rental;
	
	private Double electicityCharges;
	
	private Double distributionCharges;
	
	private Double fuleAdjustmentCharges;
	
	private Double electricityTax;
	
	private Double electricitySellTax;
	
	private Double previousBillCredit;
	
	private Double otherCharges;
	
	private Double currentInterest;
	
	private Double capacitorPenalty;
	
	private Double adjustment;
	
	private Double refundingOutstanding;
	
	private Double totalRefunding;
	
	private Double netBillAmount;
	
	private String roundedBill;
	
}
