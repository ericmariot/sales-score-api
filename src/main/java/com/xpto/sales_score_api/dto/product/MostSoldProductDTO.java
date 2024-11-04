package com.xpto.sales_score_api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MostSoldProductDTO {

    private int productId;

    private String productName;

    private long totalQuantity;
}
