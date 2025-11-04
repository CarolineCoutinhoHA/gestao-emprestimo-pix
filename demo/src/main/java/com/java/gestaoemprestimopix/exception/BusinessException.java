package com.java.gestaoemprestimopix.exception;

public class BusinessException extends ApplicationException {

    public BusinessException(String message) {
        super(message, "BUSINESS_ERROR", 400); // 400 Bad Request
    }
}
