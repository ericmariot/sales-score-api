package com.xpto.sales_score_api.dto.sale;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class SaleProductDTO {

    private long productId;

    @NotNull(message = "quantity cannot be blank")
    private int quantity;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    private BigDecimal price;

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                " productId='" + getProductId() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }
}