package com.xpto.sales_score_api.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.xpto.sales_score_api.dto.sale.SaleDTO;
import com.xpto.sales_score_api.model.Sale;
import com.xpto.sales_score_api.model.SaleProduct;
import com.xpto.sales_score_api.model.Salesperson;

public class SaleMapper {
    public static SaleDTO toDTO(Sale sale) {
        SaleDTO dto = new SaleDTO();
        dto.setId(sale.getId());
        dto.setSalespersonId(sale.getSalesperson().getId());
        dto.setTotal(sale.getTotal());
        dto.setSaleDate(sale.getSaleDate());

        dto.setProducts(
                sale.getSaleProducts()
                        .stream()
                        .map(SaleProductMapper::toDTO)
                        .collect(Collectors.toList()));

        return dto;
    }

    public static Sale toEntity(SaleDTO dto, Salesperson salesperson, Set<SaleProduct> saleProducts) {
        Sale sale = new Sale();
        sale.setId(dto.getId());
        sale.setSalesperson(salesperson);
        sale.setTotal(dto.getTotal());
        sale.setSaleDate(dto.getSaleDate());
        sale.setSaleProducts(saleProducts);

        return sale;
    }
}
