package com.xpto.sales_score_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpto.sales_score_api.dto.product.MostSoldProductDTO;
import com.xpto.sales_score_api.repo.SaleProductRepository;

@Service
public class ProductService {

    @Autowired
    private SaleProductRepository saleProductRepository;

    public List<MostSoldProductDTO> getMostSoldProducts(String order) {

        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Order must be either 'ASC' or 'DESC', but you provided: " + order);
        }

        List<Object[]> results;
        if (order.equalsIgnoreCase("ASC")) {
            results = saleProductRepository.findMostSoldProductsAsc();
        } else {
            results = saleProductRepository.findMostSoldProductsDesc();
        }

        List<MostSoldProductDTO> soldProducts = new ArrayList<>();
        for (Object[] result : results) {
            int productId = (int) result[0];
            String productName = (String) result[1];
            long totalQuantity = (long) result[2];

            soldProducts.add(new MostSoldProductDTO(productId, productName, totalQuantity));
        }

        return soldProducts;
    }
}
