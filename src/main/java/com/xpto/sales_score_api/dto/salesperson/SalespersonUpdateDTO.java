package com.xpto.sales_score_api.dto.salesperson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalespersonUpdateDTO {

    private long id;

    private String name;
    
    private String registration;
}
