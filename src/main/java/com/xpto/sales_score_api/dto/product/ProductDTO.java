package com.xpto.sales_score_api.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private long id;

    @NotNull(message = "name cannot be blank")
    private String name;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    private BigDecimal price;
}
