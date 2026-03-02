package com.pragma.tecnologia.infrastructure.exceptionhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.tecnologia.infrastructure.entities.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @NonNull
    public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {
        log.error("Error procesando request a: {} - Error: {}", exchange.getRequest().getPath(), ex.getMessage());

        return switch (ex) {
            case WebExchangeBindException webEx -> handleValidationException(webEx, exchange);
            case IllegalArgumentException illegalEx -> handleBadRequest(illegalEx, exchange, "BAD_PARAMETERS");
            default -> {
                String message = ex.getMessage() != null ? ex.getMessage() : "";

                if (message.contains("ya existe")) {
                    yield handleConflict(ex, exchange);
                }

                if (message.contains("404 NOT_FOUND") || message.contains("No encontrado") || message.contains("not found")) {
                    yield handleNotFound(ex, exchange);
                }

                yield handleGenericException(exchange);
            }
        };
    }

    private Mono<Void> handleNotFound(Throwable ex, ServerWebExchange exchange) {
        return writeResponse(exchange, HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage());
    }

    private Mono<Void> handleConflict(Throwable ex, ServerWebExchange exchange) {
        return writeResponse(exchange, HttpStatus.CONFLICT, "CONFLICT", ex.getMessage());
    }

    private Mono<Void> handleBadRequest(Throwable ex, ServerWebExchange exchange, String code) {
        return writeResponse(exchange, HttpStatus.BAD_REQUEST, code, ex.getMessage());
    }

    private Mono<Void> handleValidationException(WebExchangeBindException ex, ServerWebExchange exchange) {
        String details = ex.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return writeResponse(exchange, HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", details);
    }

    private Mono<Void> handleGenericException(ServerWebExchange exchange) {
        return writeResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "Ocurrió un error inesperado");
    }

    private Mono<Void> writeResponse(ServerWebExchange exchange, HttpStatus status, String code, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(code)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return exchange.getResponse().writeWith(Mono.fromCallable(() -> {
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(errorResponse);
                return exchange.getResponse().bufferFactory().wrap(bytes);
            } catch (Exception e) {
                log.error("Error serializando respuesta de error", e);
                return exchange.getResponse().bufferFactory().wrap("{}".getBytes());
            }
        }));
    }
}