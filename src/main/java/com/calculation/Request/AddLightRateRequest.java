package com.calculation.Request;

import lombok.Data;

@Data
public class AddLightRateRequest {

	private Integer id;

	private Integer upperBound;

	private Integer lowerBound;

	private Double lightRateInRupees;

	private Double fuleAdjustmentComeInRupees;
}
