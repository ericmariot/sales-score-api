package com.xpto.sales_score_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xpto.sales_score_api.model.Salesperson;

@Repository
public interface SalespersonRepository extends JpaRepository<Salesperson, Long>, JpaSpecificationExecutor<Salesperson> {
    @Query(value = "SELECT sale.salesperson_id, salesperson.name, COUNT(sale) " +
            "FROM sale " +
            "LEFT JOIN salesperson ON salesperson.id = sale.salesperson_id " +
            "GROUP BY sale.salesperson_id, salesperson.name " +
            "ORDER BY COUNT(sale) DESC", nativeQuery = true)
    List<Object[]> findSalesCountBySalesperson();
}
