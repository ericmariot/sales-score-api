package com.xpto.sales_score_api.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class SaleDTO {

    private long id;

    @NotNull(message = "salespersonId cannot be blank")
    private long salespersonId;

    @NotNull(message = "products cannot be blank")
    private List<SaleProductDTO> products;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    private BigDecimal total;

    private LocalDateTime saleDate;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalespersonId() {
        return this.salespersonId;
    }

    public void setSalespersonId(long salespersonId) {
        this.salespersonId = salespersonId;
    }

    public List<SaleProductDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<SaleProductDTO> products) {
        this.products = products;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getSaleDate() {
        return this.saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

}