package com.xpto.sales_score_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xpto.sales_score_api.dto.salesperson.SalesCountSalespersonDTO;
import com.xpto.sales_score_api.dto.salesperson.SalespersonDTO;
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
        try {
            return salespersonRepository.save(salesperson);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("registration already in use by another salesperson");
        }
    }

    public Salesperson updateSalesperson(Salesperson salesperson) {
        try {
            return salespersonRepository.save(salesperson);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("registration already in use by another salesperson");
        }
    }

    public List<SalesCountSalespersonDTO> getSalesCountSalesperson() {
        List<Object[]> results = salespersonRepository.findSalesCountBySalesperson();

        return results.stream()
                .map(row -> new SalesCountSalespersonDTO((long) row[0], (long) row[1]))
                .collect(Collectors.toList());
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
