package com.xpto.sales_score_api.service;

import java.util.List;

import com.xpto.sales_score_api.dto.SalespersonDTO;

public interface SalespersonServiceInterface {
    List<SalespersonDTO> findAll(String registration);
}