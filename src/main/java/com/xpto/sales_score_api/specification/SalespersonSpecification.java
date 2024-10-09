package com.xpto.sales_score_api.specification;

import org.springframework.data.jpa.domain.Specification;

import com.xpto.sales_score_api.model.Salesperson;

public class SalespersonSpecification {
    private SalespersonSpecification() {}
    
    public static Specification<Salesperson> registration(String registration) {
        return (root, query, builder) -> builder.like(root.get("registration"), "%" + registration + "%");
    }
}
