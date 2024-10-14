package com.xpto.sales_score_api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String element, Long id) {
        super(element + " not found with id: " + id);
    }
}