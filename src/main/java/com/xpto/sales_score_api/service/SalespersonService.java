package com.xpto.sales_score_api.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xpto.sales_score_api.dto.SalespersonDTO;
import com.xpto.sales_score_api.mapper.SalespersonMapper;
import com.xpto.sales_score_api.model.Salesperson;
import com.xpto.sales_score_api.repo.SalespersonRepository;
import com.xpto.sales_score_api.specification.SalespersonSpecification;

import io.micrometer.common.util.StringUtils;

@Service
public class SalespersonService implements SalespersonServiceInterface {

    private final SalespersonRepository salespersonRepository;

    public SalespersonService(SalespersonRepository salespersonRepository) {
        this.salespersonRepository = salespersonRepository;
    }

    public Salesperson createSalesperson(Salesperson salesperson) {
        if (salespersonRepository.existsByRegistration(salesperson.getRegistration())) {
            throw new IllegalArgumentException("registration already in use");
        }

        return salespersonRepository.save(salesperson);
    }

    @Override
    public List<SalespersonDTO> findAll(String registration) {
        Specification<Salesperson> filters = Specification
                .where(StringUtils.isBlank(registration) ? null : SalespersonSpecification.registration(registration));

        return salespersonRepository.findAll(filters)
                .stream()
                .map(SalespersonMapper::toDTO)
                .toList();
    }
}
