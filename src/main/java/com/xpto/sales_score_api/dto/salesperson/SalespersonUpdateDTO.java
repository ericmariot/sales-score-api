package com.xpto.sales_score_api.dto.salesperson;

public class SalespersonUpdateDTO {

    private long id;

    private String name;

    private String registration;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return this.registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

}
