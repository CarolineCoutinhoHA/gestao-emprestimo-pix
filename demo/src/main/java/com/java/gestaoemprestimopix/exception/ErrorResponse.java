package com.java.gestaoemprestimopix.exception;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        LocalDateTime timestamp, // Quando o erro ocorreu
        int status,             // Código HTTP (404, 400, 500)
        String error,           // Nome do erro ("Not Found", "Bad Request")
        String errorCode,       // Código interno ("RESOURCE_NOT_FOUND", "BUSINESS_ERROR")
        String message,         // Mensagem detalhada em português
        String path            // URI que causou o erro
) {}
