package com.xpto.sales_score_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpto.sales_score_api.model.SaleProduct;
import com.xpto.sales_score_api.model.SaleProductId;

public interface SaleProductRepository extends JpaRepository<SaleProduct, SaleProductId> {
}
