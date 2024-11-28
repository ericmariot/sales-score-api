package com.xpto.sales_score_api.dto.salesperson;

public class SalesCountSalespersonDTO {

    public SalesCountSalespersonDTO() {
    }

    public SalesCountSalespersonDTO(int salespersonId, String salesperson, long sales) {
        this.salespersonId = salespersonId;
        this.salesperson = salesperson;
        this.sales = sales;
    }

    private int salespersonId;

    private String salesperson;

    private long sales;

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

    public long getSales() {
        return this.sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

}
