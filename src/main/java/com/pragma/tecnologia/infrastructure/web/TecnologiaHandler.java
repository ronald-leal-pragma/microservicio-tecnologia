package com.pragma.tecnologia.infrastructure.web;

import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.infrastructure.mappers.TecnologiaMapper;
import com.pragma.tecnologia.infrastructure.requests.TecnologiaRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class TecnologiaHandler {
    private static final String TIME_STAMP = "timestamp";

    private final ITecnologiaServicePort tecnologiaServicePort;
    private final TecnologiaMapper tecnologiaMapper;
    private final Validator validator;

    public Mono<ServerResponse> saveTecnologia(ServerRequest request) {
        return request.bodyToMono(TecnologiaRequest.class)
                .doOnSubscribe(s -> log.info("Method: saveTecnologia - Input: path={}", request.path()))
                .doOnNext(dto -> log.info("Method: saveTecnologia - Request body: {}", dto))
                .flatMap(this::validateRequest)
                .map(tecnologiaMapper::toDomain)
                .flatMap(tecnologiaServicePort::saveTecnologia)
                .flatMap(saved -> ServerResponse
                        .created(URI.create("/api/tecnologia/" + saved.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(saved)
                )
                .doOnSuccess(response -> log.info("Method: saveTecnologia - Output: status=201"))
                .doOnError(error -> log.error("Method: saveTecnologia - Error: {}", error.getMessage()));
    }

    public Mono<ServerResponse> getTecnologiaById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));

        return Mono.just(id)
                .doOnSubscribe(s -> log.info("Method: getTecnologiaById - Input: id={}", id))
                .flatMap(tecnologiaServicePort::getTecnologiaById)
                .flatMap(tecnologia -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(tecnologia)
                )
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Method: getTecnologiaById - Resource not found for id: {}", id);
                    return ServerResponse.status(HttpStatus.NOT_FOUND)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(Map.of(
                                    "code", "NOT_FOUND",
                                    "message", "404 NOT_FOUND",
                                    TIME_STAMP, LocalDateTime.now().toString()
                            ));
                }))
                .doOnSuccess(response -> log.info("Method: getTecnologiaById - Output: status={}", response.statusCode()))
                .doOnError(error -> log.error("Method: getTecnologiaById - Error: {}", error.getMessage()));
    }

    public Mono<ServerResponse> deleteTecnologia(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));

        return Mono.just(id)
                .doOnSubscribe(s -> log.info("Method: deleteTecnologia - Input: id={}", id))
                .flatMap(tecnologiaServicePort::deleteTecnologia)
                .then(ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of(
                                "mensaje", "Tecnología eliminada con éxito",
                                "id", id,
                                TIME_STAMP, LocalDateTime.now().toString()
                        )))
                .onErrorResume(e -> {
                    if (e.getMessage().contains("no existe")) {
                        log.warn("Method: deleteTecnologia - Tecnología no encontrada: {}", id);
                        return ServerResponse.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of(
                                        "code", "NOT_FOUND",
                                        "message", "404 NOT_FOUND",
                                        TIME_STAMP, LocalDateTime.now().toString()
                                ));
                    }
                    log.error("Method: deleteTecnologia - Error al eliminar tecnología {}: {}", id, e.getMessage());
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(Map.of(
                                    "error", "ERROR_ELIMINACION",
                                    "mensaje", "Error al eliminar la tecnología: " + e.getMessage()
                            ));
                })
                .doOnSuccess(response -> log.info("Method: deleteTecnologia - Output: status={}", response.statusCode()))
                .doOnError(error -> log.error("Method: deleteTecnologia - Error: {}", error.getMessage()));
    }

    private Mono<TecnologiaRequest> validateRequest(TecnologiaRequest request) {
        Set<ConstraintViolation<TecnologiaRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));
            log.warn("Method: validateRequest - Validation failed: {}", errors);
            return Mono.error(new IllegalArgumentException(errors));
        }
        return Mono.just(request);
    }

    public Mono<ServerResponse> getAllTecnologias(ServerRequest request) {
        return request.queryParam("ids")
                .map(idsParam -> {
                    try {
                        Set<Long> ids = stream(idsParam.split(","))
                                .map(String::trim)
                                .map(Long::parseLong)
                                .collect(Collectors.toSet());
                        
                        log.info("Method: getAllTecnologias - Filtrando por {} IDs", ids.size());
                        return tecnologiaServicePort.getTecnologiasByIds(ids);
                    } catch (NumberFormatException e) {
                        log.error("Method: getAllTecnologias - IDs inválidos: {}", idsParam);
                        return tecnologiaServicePort.getAllTecnologias();
                    }
                })
                .orElseGet(() -> {
                    log.info("Method: getAllTecnologias - Obteniendo todas las tecnologías");
                    return tecnologiaServicePort.getAllTecnologias();
                })
                .collectList()
                .flatMap(tecnologias -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(tecnologias)
                )
                .doOnSuccess(response -> log.info("Method: getAllTecnologias - Output: status={}", response.statusCode()))
                .doOnError(error -> log.error("Method: getAllTecnologias - Error: {}", error.getMessage()));
    }
}
