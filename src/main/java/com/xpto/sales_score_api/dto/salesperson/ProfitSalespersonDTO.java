package com.xpto.sales_score_api.dto.salesperson;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSalespersonDTO {

    private int salespersonId;

    private String salesperson;

    private long total;

    private BigDecimal sales;
}
