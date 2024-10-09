package com.xpto.sales_score_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalespersonDTO {

    private long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "registration is mandatory")
    private String registration;
}
