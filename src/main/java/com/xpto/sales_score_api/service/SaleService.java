package com.xpto.sales_score_api.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpto.sales_score_api.dto.sale.SaleDTO;
import com.xpto.sales_score_api.dto.sale.SaleProductDTO;
import com.xpto.sales_score_api.exception.NotFoundException;
import com.xpto.sales_score_api.mapper.SaleMapper;
import com.xpto.sales_score_api.mapper.SaleProductMapper;
import com.xpto.sales_score_api.model.Product;
import com.xpto.sales_score_api.model.Sale;
import com.xpto.sales_score_api.model.SaleProduct;
import com.xpto.sales_score_api.model.Salesperson;
import com.xpto.sales_score_api.repo.ProductRepository;
import com.xpto.sales_score_api.repo.SaleRepository;
import com.xpto.sales_score_api.repo.SalespersonRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SalespersonRepository salespersonRepository;

    @Autowired
    private ProductRepository productRepository;

    public SaleDTO createSale(SaleDTO saleDTO) {
        long salespersonId = saleDTO.getSalespersonId();

        Salesperson salesperson = salespersonRepository.findById(salespersonId)
                .orElseThrow(() -> new NotFoundException("salesperson", salespersonId));

        Set<SaleProduct> saleProducts = new HashSet<>();
        BigDecimal saleTotal = BigDecimal.ZERO;

        Sale sale = SaleMapper.toEntity(saleDTO, salesperson, saleProducts);

        for (SaleProductDTO saleProductDTO : saleDTO.getProducts()) {
            long productId = saleProductDTO.getProductId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("product", productId));

            SaleProduct saleProduct = SaleProductMapper.toEntity(saleProductDTO, product);
            saleProduct.setPrice(product.getPrice());
            saleProduct.setSale(sale);

            saleProduct.calculateTotal();
            saleTotal = saleTotal.add(saleProduct.getTotal());

            saleProducts.add(saleProduct);
        }

        sale.setTotal(saleTotal);

        Sale savedSale = saleRepository.save(sale);
        long savedSaleId = savedSale.getId();
        savedSale = saleRepository.findById(savedSaleId).orElseThrow(() -> new NotFoundException("sale", savedSaleId));

        return SaleMapper.toDTO(savedSale);
    }
}
