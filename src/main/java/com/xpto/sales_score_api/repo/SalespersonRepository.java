package com.xpto.sales_score_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xpto.sales_score_api.model.Salesperson;

@Repository
public interface SalespersonRepository extends JpaRepository<Salesperson, Long>, JpaSpecificationExecutor<Salesperson> {
    @Query(value = "SELECT s.salesperson.id, COUNT(s) FROM Sale s GROUP BY s.salesperson.id ORDER BY COUNT(s) DESC")
    List<Object[]> findSalesCountBySalesperson();
}
