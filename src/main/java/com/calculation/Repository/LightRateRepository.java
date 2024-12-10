package com.calculation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calculation.Entity.LightRate;


@Repository
public interface LightRateRepository extends JpaRepository<LightRate, Integer> {

	LightRate findFirstByIsDeletedFalse();

}
