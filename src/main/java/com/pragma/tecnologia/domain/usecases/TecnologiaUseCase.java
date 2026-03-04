package com.pragma.tecnologia.domain.usecases;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
public class TecnologiaUseCase implements ITecnologiaServicePort {

    private final ITecnologiaPersistencePort tecnologiaPersistencePort;

    public TecnologiaUseCase(ITecnologiaPersistencePort tecnologiaPersistencePort) {
        this.tecnologiaPersistencePort = tecnologiaPersistencePort;
    }

    @Override
    public Mono<Tecnologia> saveTecnologia(Tecnologia tecnologia) {
        return tecnologiaPersistencePort.findByName(tecnologia.getNombre())
                .doOnSubscribe(s -> log.info("UseCase: saveTecnologia - Iniciando validación para: {}", tecnologia.getNombre()))
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(existingOpt -> {
                    if (existingOpt.isPresent()) {
                        log.warn("UseCase: saveTecnologia - Conflicto: La tecnología '{}' ya existe", tecnologia.getNombre());
                        return Mono.error(new RuntimeException("El nombre de la Tecnologia ya existe"));
                    }

                    log.info("UseCase: saveTecnologia - Nombre disponible, guardando: {}", tecnologia.getNombre());
                    return tecnologiaPersistencePort.save(tecnologia);
                })
                .doOnSuccess(saved -> log.info("UseCase: saveTecnologia - Guardado exitoso con ID: {}", saved.getId()))
                .doOnError(error -> log.error("UseCase: saveTecnologia - Error en proceso: {}", error.getMessage()));
    }

    @Override
    public Mono<Tecnologia> getTecnologiaById(Long id) {
        return tecnologiaPersistencePort.getTecnologiaById(id)
                .doOnSubscribe(s -> log.info("UseCase: getTecnologiaById - Buscando ID: {}", id))
                .doOnNext(t -> log.info("UseCase: getTecnologiaById - Encontrada: {}", t.getNombre()))
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("UseCase: getTecnologiaById - No se encontró la tecnología con ID: {}", id);
                    return Mono.empty();
                }));
    }

    @Override
    @Transactional
    public Mono<Void> deleteTecnologia(Long id) {
        log.info("UseCase: deleteTecnologia - Iniciando eliminación de tecnología con ID: {}", id);
        return tecnologiaPersistencePort.getTecnologiaById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("La tecnologia con ID " + id + " no existe")))
                .flatMap(tecnologia -> {
                    log.info("UseCase: deleteTecnologia - Eliminando tecnología: {}", tecnologia.getNombre());
                    return tecnologiaPersistencePort.deleteTecnologia(id);
                })
                .doOnSuccess(v -> log.info("UseCase: deleteTecnologia - Tecnología eliminada exitosamente"))
                .doOnError(error -> log.error("UseCase: deleteTecnologia - Error: {}", error.getMessage()));
    }

    @Override
    public reactor.core.publisher.Flux<Tecnologia> getAllTecnologias() {
        return tecnologiaPersistencePort.findAll()
                .doOnSubscribe(s -> log.info("UseCase: getAllTecnologias - Obteniendo todas las tecnologías"))
                .doOnComplete(() -> log.info("UseCase: getAllTecnologias - Listado completo"))
                .doOnError(error -> log.error("UseCase: getAllTecnologias - Error: {}", error.getMessage()));
    }

    @Override
    public Flux<Tecnologia> getTecnologiasByIds(java.util.Set<Long> ids) {
        log.info("UseCase: getTecnologiasByIds - Filtrando {} tecnologías", ids.size());
        return tecnologiaPersistencePort.findAll()
                .filter(tech -> ids.contains(tech.getId()))
                .doOnComplete(() -> log.info("UseCase: getTecnologiasByIds - Filtrado completado"));
    }
}
