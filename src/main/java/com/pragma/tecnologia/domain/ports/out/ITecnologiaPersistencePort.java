package com.pragma.tecnologia.domain.ports.out;

import com.pragma.tecnologia.domain.models.Tecnologia;
import reactor.core.publisher.Mono;

public interface ITecnologiaPersistencePort {
    Mono<Tecnologia> save(Tecnologia tecnologia);
    Mono<Tecnologia> findByName(String nombre);
    Mono<Tecnologia> getTecnologiaById(Long id);
    Mono<Void> deleteTecnologia(Long id);
}
