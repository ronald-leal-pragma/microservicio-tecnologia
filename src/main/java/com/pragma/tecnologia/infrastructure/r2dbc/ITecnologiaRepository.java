package com.pragma.tecnologia.infrastructure.r2dbc;

import com.pragma.tecnologia.infrastructure.entities.TecnologiaEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ITecnologiaRepository extends R2dbcRepository<TecnologiaEntity, Long> {
    Mono<TecnologiaEntity> findByNombre(String nombre);
}
