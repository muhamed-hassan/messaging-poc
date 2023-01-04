package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.CashOutReport;

@Repository
public interface CashOutReportRepository extends JpaRepository<CashOutReport, Integer> {

	CashOutReport findByYear(int year);
	
}
