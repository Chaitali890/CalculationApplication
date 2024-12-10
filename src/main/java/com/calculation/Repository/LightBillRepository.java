package com.calculation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculation.Entity.LightBill;


public interface LightBillRepository extends JpaRepository<LightBill, Integer> {

	LightBill findFirstByIsDeletedFalse();

}
