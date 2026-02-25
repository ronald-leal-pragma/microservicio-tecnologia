package com.pragma.tecnologia.domain.ports.in;

import com.pragma.tecnologia.domain.models.Tecnologia;
import reactor.core.publisher.Mono;

public interface ITecnologiaServicePort {
    Mono<Tecnologia> saveTecnologia(Tecnologia tecnologia);
    Mono<Tecnologia> getTecnologiaById(Long id);
}
