package com.xpto.sales_score_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.xpto.sales_score_api.model.Salesperson;

@Repository
public interface SalespersonRepository extends JpaRepository<Salesperson, Long>, JpaSpecificationExecutor<Salesperson> {
}
