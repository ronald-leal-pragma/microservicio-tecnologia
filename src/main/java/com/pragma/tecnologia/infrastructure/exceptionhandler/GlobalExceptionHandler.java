package com.pragma.tecnologia.infrastructure.exceptionhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {
    
    private static final String ERROR ="error";
    private static final String MESSAGE ="message";

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("path", exchange.getRequest().getPath().value());

        if (ex instanceof WebExchangeBindException validationEx) {
            status = HttpStatus.BAD_REQUEST;
            String errors = validationEx.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            body.put(ERROR, "Error de validación");
            body.put(MESSAGE, errors);
        } else if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            body.put(ERROR, "Error en los datos");
            body.put(MESSAGE, ex.getMessage());
        } else if (ex.getMessage() != null && ex.getMessage().contains("ya existe")) {
            status = HttpStatus.CONFLICT;
            body.put(ERROR, "Conflicto");
            body.put(MESSAGE, ex.getMessage());
        } else {
            body.put(ERROR, "Error interno");
            body.put(MESSAGE, "Ocurrió un error inesperado");
        }

        body.put("status", status.value());
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return Mono.fromCallable(() -> objectMapper.writeValueAsBytes(body))
                .map(bytes -> exchange.getResponse().bufferFactory().wrap(bytes))
                .flatMap(buffer -> exchange.getResponse().writeWith(Mono.just(buffer)));
    }
}

