package com.pragma.tecnologia.infrastructure.web;

import com.pragma.tecnologia.application.dtos.requests.TecnologiaRequest;
import com.pragma.tecnologia.application.mappers.TecnologiaMapper;
import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TecnologiaHandler {

    private final ITecnologiaServicePort tecnologiaServicePort;
    private final TecnologiaMapper tecnologiaMapper;
    private final Validator validator;

    public Mono<ServerResponse> saveTecnologia(ServerRequest request) {
        return request.bodyToMono(TecnologiaRequest.class)
                .flatMap(tecnologiaRequest -> {
                    Set<ConstraintViolation<TecnologiaRequest>> violations = validator.validate(tecnologiaRequest);
                    if (!violations.isEmpty()) {
                        String errors = violations.stream()
                                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                                .collect(Collectors.joining(", "));
                        return Mono.error(new IllegalArgumentException(errors));
                    }
                    return Mono.just(tecnologiaRequest);
                })
                .map(tecnologiaMapper::toDomain)
                .flatMap(tecnologiaServicePort::saveTecnologia)
                .flatMap(saved -> ServerResponse
                        .created(URI.create("/api/tecnologia/" + saved.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(saved));
    }

    public Mono<ServerResponse> getTecnologiaById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return tecnologiaServicePort.getTecnologiaById(id)
                .flatMap(tecnologia -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(tecnologia))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
