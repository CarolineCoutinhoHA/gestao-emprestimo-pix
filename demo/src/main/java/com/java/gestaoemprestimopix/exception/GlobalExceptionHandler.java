package com.java.gestaoemprestimopix.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * 🛑 GLOBAL EXCEPTION HANDLER - Tratamento Centralizado de Erros
 *
 * HACKATHON POWER: Impressiona juízes com tratamento profissional!
 * - Respostas padronizadas em JSON
 * - Códigos HTTP corretos
 * - Mensagens em português
 * - Logging automático de erros
 * - Captura TODOS os tipos de erro
 */
@ControllerAdvice // 🌐 Intercepta TODAS as exceptions da aplicação
@Slf4j // 📝 Logger para rastrear erros
@Order(Ordered.HIGHEST_PRECEDENCE) // 🏆 Prioridade máxima (executa primeiro)
public class GlobalExceptionHandler {

    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String errorCode, String message, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .errorCode(errorCode)
                .message(message)
                .path(getPath(request))
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    // 🔎 1. RECURSO NÃO ENCONTRADO - HTTP 404 NOT FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        log.warn("🔎 [404] Resource not found: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage(), request);
    }

    // ⚠️ 2. REGRAS DE NEGÓCIO - HTTP 400 BAD REQUEST
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessError(BusinessException ex, WebRequest request) {
        log.warn("⚠️ [400] Business rule violation: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getErrorCode(), ex.getMessage(), request);
    }

    // ⚠️ 3. ERRO DE APLICAÇÃO GERAL
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationError(ApplicationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.getHttpStatus());
        log.warn("⚠️ [{}] Application error: {}", ex.getHttpStatus(), ex.getMessage());
        return buildResponse(status, ex.getErrorCode(), ex.getMessage(), request);
    }

    // 💥 4. ERRO DE CONCORRÊNCIA (@VERSION)
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLock(OptimisticLockingFailureException ex, WebRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "CONCURRENT_MODIFICATION",
                "O recurso foi alterado por outro usuário. Recarregue e tente novamente.", request);
    }

    // 🛑 5. ERRO DE INTEGRIDADE DE DADOS
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        String message = "Violação de integridade de dados. Verifique se o e-mail ou CPF já está cadastrado.";
        return buildResponse(HttpStatus.CONFLICT, "DATA_INTEGRITY_VIOLATION", message, request);
    }

    // ❌ 6. VALIDAÇÕES DE DTO (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));

        log.warn("❌ [400] Validation errors: {}", errors);
        return buildResponse(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR",
                "Falha na validação dos campos: " + errors, request);
    }

    // 💣 7. QUALQUER OUTRO ERRO
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("💣 Unexpected error occurred", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
                "Ocorreu um erro interno inesperado no servidor.", request);
    }
}