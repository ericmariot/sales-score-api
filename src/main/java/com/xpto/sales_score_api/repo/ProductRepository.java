package com.xpto.sales_score_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpto.sales_score_api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
