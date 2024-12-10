package com.calculation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GoldCheck {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "gram")
	private Integer gram;
	
	@Column(name = "miligram")
	private Integer miligram;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "month")
	private String month;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name = "isDeleted" , columnDefinition =" boolean default 0")
	private Boolean isDeleted = false;
	
}
