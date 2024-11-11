package com.xpto.sales_score_api.exception;

public class SalespersonInUseException extends RuntimeException {
    public SalespersonInUseException(String message) {
        super(message);
    }
}