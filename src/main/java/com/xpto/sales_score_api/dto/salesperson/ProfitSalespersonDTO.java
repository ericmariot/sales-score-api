package com.xpto.sales_score_api.dto.salesperson;

import java.math.BigDecimal;

public class ProfitSalespersonDTO {

    public ProfitSalespersonDTO() {
    }

    public ProfitSalespersonDTO(int salespersonId, String salesperson, long total, BigDecimal sales) {
        this.salespersonId = salespersonId;
        this.salesperson = salesperson;
        this.total = total;
        this.sales = sales;
    }

    private int salespersonId;

    private String salesperson;

    private long total;

    private BigDecimal sales;

    public int getSalespersonId() {
        return this.salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }

    public String getSalesperson() {
        return this.salesperson;
    }

    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public BigDecimal getSales() {
        return this.sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

}
