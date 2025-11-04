package com.java.gestaoemprestimopix.exception;


public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String message) {
        super(message, "RESOURCE_NOT_FOUND", 404); // 404 Not Found
    }
}
