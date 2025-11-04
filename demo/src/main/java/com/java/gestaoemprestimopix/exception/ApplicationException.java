package com.java.gestaoemprestimopix.exception;

public abstract class ApplicationException extends RuntimeException {

    private final String errorCode; // Ex: "VALIDATION_ERROR", "BUSINESS_ERROR"
    private final int httpStatus;   // Código HTTP associado (400, 404, 500)

    // Construtor padrão (usado pelas subclasses)
    protected ApplicationException(String message) {
        super(message);
        this.errorCode = "APPLICATION_ERROR";
        this.httpStatus = 500; // Internal Server Error por padrão
    }

    // Construtor completo (controle total)
    protected ApplicationException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    // Getters para o GlobalExceptionHandler
    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
