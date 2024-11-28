package com.xpto.sales_score_api.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class SaleProductId implements Serializable {

    private long saleId;

    private long productId;

    public long getSaleId() {
        return this.saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

}
