package com.xpto.sales_score_api.exception;

public class SalespersonNotFoundException extends RuntimeException {
    public SalespersonNotFoundException(Long id) {
        super("Salesperson not found with id: " + id);
    }
}