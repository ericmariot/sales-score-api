package com.xpto.sales_score_api.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class SaleProductId implements Serializable {

    private long saleId;

    private long productId;

}
