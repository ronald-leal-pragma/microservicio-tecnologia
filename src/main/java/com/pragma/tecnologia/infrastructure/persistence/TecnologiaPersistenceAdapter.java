package com.pragma.tecnologia.infrastructure.persistence;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import com.pragma.tecnologia.infrastructure.mappers.TecnologiaEntityMapper;
import com.pragma.tecnologia.infrastructure.r2dbc.ITecnologiaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class TecnologiaPersistenceAdapter implements ITecnologiaPersistencePort {

    private static final String SERVICE_OPERATION_DB = "tecnologia-db";

    private final ITecnologiaRepository tecnologiaRepository;
    private final TecnologiaEntityMapper tecnologiaEntityMapper;

    @Override
    @CircuitBreaker(name = SERVICE_OPERATION_DB)
    public Mono<Tecnologia> save(Tecnologia tecnologia) {
        log.debug("Persistence: Guardando tecnología: {}", tecnologia.getNombre());
        return Mono.just(tecnologia)
                .map(tecnologiaEntityMapper::toEntity)
                .flatMap(tecnologiaRepository::save)
                .map(tecnologiaEntityMapper::toDomain)
                .doOnError(e -> log.error("Persistence: Error al guardar tecnología '{}': {}",
                        tecnologia.getNombre(), e.getMessage()));
    }

    @Override
    @CircuitBreaker(name = SERVICE_OPERATION_DB)
    public Mono<Tecnologia> findByName(String nombre) {
        log.debug("Persistence: Buscando tecnología por nombre: {}", nombre);
        return tecnologiaRepository.findByNombre(nombre)
                .map(tecnologiaEntityMapper::toDomain)
                .doOnNext(t -> log.debug("Persistence: Tecnología encontrada: {}", t.getNombre()));
    }

    @Override
    @CircuitBreaker(name = SERVICE_OPERATION_DB)
    public Mono<Tecnologia> getTecnologiaById(Long id) {
        log.debug("Persistence: Buscando tecnología por ID: {}", id);
        return tecnologiaRepository.findById(id)
                .map(tecnologiaEntityMapper::toDomain)
                .doOnNext(t -> log.debug("Persistence: Tecnología recuperada: {}", t.getNombre()))
                .doOnError(e -> log.error("Persistence: Error al buscar tecnología ID {}: {}",
                        id, e.getMessage()));
    }

    @Override
    @CircuitBreaker(name = SERVICE_OPERATION_DB)
    public Mono<Void> deleteTecnologia(Long id) {
        log.debug("Persistence: Eliminando tecnología con ID: {}", id);
        return tecnologiaRepository.deleteById(id)
                .doOnSuccess(v -> log.info("Persistence: Tecnología con ID {} eliminada exitosamente", id))
                .doOnError(e -> log.error("Persistence: Error al eliminar tecnología ID {}: {}", id, e.getMessage()));
    }

    @Override
    @CircuitBreaker(name = SERVICE_OPERATION_DB)
    public Flux<Tecnologia> findAll() {
        log.debug("Persistence: Obteniendo todas las tecnologías");
        return tecnologiaRepository.findAll()
                .map(tecnologiaEntityMapper::toDomain)
                .doOnComplete(() -> log.debug("Persistence: Listado de tecnologías completado"))
                .doOnError(e -> log.error("Persistence: Error al listar tecnologías: {}", e.getMessage()));
    }
}
