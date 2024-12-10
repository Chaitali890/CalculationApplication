package com.calculation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calculation.Entity.RechargePlan;


@Repository
public interface RechargePlanRepository extends JpaRepository<RechargePlan, Integer> {

}
