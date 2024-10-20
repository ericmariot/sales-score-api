package com.xpto.sales_score_api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sale_product")
public class SaleProduct {

    @EmbeddedId
    private SaleProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("saleId")
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "quantity cannot be blank")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "total cannot be blank")
    @DecimalMin(value = "0.0", message = "total must be greater than 0")
    @Column(insertable = false, updatable = false)
    private BigDecimal total;

    public void calculateTotal() {
        if (this.price != null && this.quantity > 0) {
            this.total = this.price.multiply(BigDecimal.valueOf(this.quantity));
        } else {
            this.total = BigDecimal.ZERO;
        }
    }
}
