package com.xpto.sales_score_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xpto.sales_score_api.model.SaleProduct;
import com.xpto.sales_score_api.model.SaleProductId;

public interface SaleProductRepository extends JpaRepository<SaleProduct, SaleProductId> {

    @Query(value = "SELECT sale_product.product_id, product.name, SUM(sale_product.quantity) AS total_quantity " +
            "FROM sale_product " +
            "LEFT JOIN product ON product.id = sale_product.product_id " +
            "GROUP BY sale_product.product_id, product.name " +
            "ORDER BY total_quantity DESC", nativeQuery = true)
    List<Object[]> findMostSoldProductsDesc();

    @Query(value = "SELECT sale_product.product_id, product.name, SUM(sale_product.quantity) AS total_quantity " +
            "FROM sale_product " +
            "LEFT JOIN product ON product.id = sale_product.product_id " +
            "GROUP BY sale_product.product_id, product.name " +
            "ORDER BY total_quantity ASC", nativeQuery = true)
    List<Object[]> findMostSoldProductsAsc();
}
