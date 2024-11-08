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

    private long salespersonId;

    private long sales;
}
