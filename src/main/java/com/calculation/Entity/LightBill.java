package com.calculation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LightBill {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "rental")
	private Double rental;
	
	@Column(name = "electicity_charges")
	private Double electicityCharges;
	
	@Column(name = "distribution_charges")
	private Double distributionCharges;
	
	@Column(name = "fuel_adjustment_charges")
	private Double fuleAdjustmentCharges;

	@Column(name = "electicity_tax")
	private Double electricityTax;
	
	@Column(name = "electricity_sell_tax")
	private Double electricitySellTax;
	
	@Column(name = "previous_bill_credit")
	private Double previousBillCredit;
	
	@Column(name = "other_charges")
	private Double otherCharges;
	
	@Column(name = "current_interest")
	private Double currentInterest;
	
	@Column(name = "capacitory_penalty")
	private Double capacitorPenalty;
	
	@Column(name = "adjustment")
	private Double adjustment;
	
	@Column(name = "refunding_outstanding")
	private Double refundingOutstanding;
	
	@Column(name = "total_refunding")
	private Double totalRefunding;
	
	@Column(name = "net_bill_amount")
	private Double netBillAmount;
	
	@Column(name = "rounded_bill")
	private Double roundedBill;
	
	@Column(name = "is_deleted", columnDefinition  = "boolean default 0")
	private Boolean isDeleted = false;
	
}
