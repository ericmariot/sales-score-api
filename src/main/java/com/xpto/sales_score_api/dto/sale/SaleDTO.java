package com.xpto.sales_score_api.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}