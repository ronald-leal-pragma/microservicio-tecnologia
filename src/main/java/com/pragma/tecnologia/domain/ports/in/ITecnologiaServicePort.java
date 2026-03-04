package com.pragma.tecnologia.domain.ports.in;

import com.pragma.tecnologia.domain.models.Tecnologia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface ITecnologiaServicePort {
    Mono<Tecnologia> saveTecnologia(Tecnologia tecnologia);
    Mono<Tecnologia> getTecnologiaById(Long id);
    Mono<Void> deleteTecnologia(Long id);
    Flux<Tecnologia> getAllTecnologias();
    Flux<Tecnologia> getTecnologiasByIds(Set<Long> ids);
}
