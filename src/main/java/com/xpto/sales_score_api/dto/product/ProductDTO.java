package com.xpto.sales_score_api.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    private long id;

    @NotNull(message = "name cannot be blank")
    private String name;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    private BigDecimal price;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
