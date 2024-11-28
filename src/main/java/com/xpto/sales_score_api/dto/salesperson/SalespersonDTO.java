package com.xpto.sales_score_api.dto.salesperson;

import jakarta.validation.constraints.NotNull;

public class SalespersonDTO {

    private long id;

    @NotNull(message = "name cannot be blank")
    private String name;

    @NotNull(message = "registration cannot be blank")
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
