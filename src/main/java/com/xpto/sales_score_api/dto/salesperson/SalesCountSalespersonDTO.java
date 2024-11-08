package com.xpto.sales_score_api.dto.salesperson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesCountSalespersonDTO {

    private int salespersonId;

    private String salesperson;

    private long sales;
}
