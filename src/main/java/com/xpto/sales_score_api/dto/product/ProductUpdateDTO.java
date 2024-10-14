package com.xpto.sales_score_api.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {

    private long id;

    private String name;

    @DecimalMin(value = "0.0", message = "price must be greater than 0")
    private BigDecimal price;
}
