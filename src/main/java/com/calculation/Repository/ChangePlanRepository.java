package com.calculation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calculation.Entity.ChangePlan;


@Repository
public interface ChangePlanRepository extends JpaRepository<ChangePlan, Integer> {

}
