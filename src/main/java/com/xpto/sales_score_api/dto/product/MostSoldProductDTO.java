package com.xpto.sales_score_api.dto.product;

public class MostSoldProductDTO {

    public MostSoldProductDTO() {
    }

    public MostSoldProductDTO(int productId, String productName, long totalQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
    }

    private int productId;

    private String productName;

    private long totalQuantity;

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

}
