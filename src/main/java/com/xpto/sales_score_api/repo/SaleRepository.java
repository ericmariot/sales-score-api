package com.xpto.sales_score_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpto.sales_score_api.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
