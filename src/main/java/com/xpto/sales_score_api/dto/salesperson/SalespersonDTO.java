package com.xpto.sales_score_api.dto.salesperson;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalespersonDTO {

    private long id;

    @NotNull(message = "name can not be blank")
    private String name;

    @NotNull(message = "registration can not be blank")
    private String registration;
}
