package com.calculation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calculation.Entity.GoldCheck;


@Repository
public interface GoldCheckRepository extends JpaRepository<GoldCheck, Integer>
{

	@Query(value = "select * from gold_check where is_deleted = '0' AND gram = :gram ", nativeQuery = true)
	Optional<GoldCheck> findByIsDeletedAndGram(Integer gram);

	GoldCheck findFirstByIsDeletedFalse();

}
