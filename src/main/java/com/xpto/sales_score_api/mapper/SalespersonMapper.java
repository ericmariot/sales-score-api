package com.xpto.sales_score_api.mapper;

import com.xpto.sales_score_api.dto.SalespersonDTO;
import com.xpto.sales_score_api.model.Salesperson;

public class SalespersonMapper {
    public static SalespersonDTO toDTO(Salesperson salesperson) {
        SalespersonDTO dto = new SalespersonDTO();
        dto.setId(salesperson.getId());
        dto.setName(salesperson.getName());
        dto.setRegistration(salesperson.getRegistration());
        return dto;
    }

    public static Salesperson toEntity(SalespersonDTO dto) {
        Salesperson salesperson = new Salesperson();
        salesperson.setName(dto.getName());
        salesperson.setRegistration(dto.getRegistration());
        return salesperson;
    }
}

