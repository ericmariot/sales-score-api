package com.xpto.sales_score_api.dto.sale;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleProductDTO {

    private long productId;

    @NotNull(message = "quantity cannot be blank")
    private int quantity;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    private BigDecimal price;
}