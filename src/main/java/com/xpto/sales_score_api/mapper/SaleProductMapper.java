package com.xpto.sales_score_api.mapper;

import com.xpto.sales_score_api.dto.sale.SaleProductDTO;
import com.xpto.sales_score_api.model.Product;
import com.xpto.sales_score_api.model.SaleProduct;
import com.xpto.sales_score_api.model.SaleProductId;

public class SaleProductMapper {

    public static SaleProductDTO toDTO(SaleProduct saleProduct) {
        SaleProductDTO dto = new SaleProductDTO();
        dto.setPrice(saleProduct.getPrice());
        dto.setQuantity(saleProduct.getQuantity());
        dto.setProductId(saleProduct.getProduct().getId());
        return dto;
    }

    public static SaleProduct toEntity(SaleProductDTO dto, Product product) {
        SaleProduct saleProduct = new SaleProduct();
        saleProduct.setProduct(product);
        saleProduct.setQuantity(dto.getQuantity());
        saleProduct.setPrice(dto.getPrice());

        SaleProductId saleProductId = new SaleProductId();
        saleProductId.setProductId(product.getId());
        saleProduct.setId(saleProductId);

        saleProduct.calculateTotal();
        return saleProduct;
    }
}
