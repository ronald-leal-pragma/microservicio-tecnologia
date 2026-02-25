package com.pragma.tecnologia.domain.usecases;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import reactor.core.publisher.Mono;

public class TecnologiaUseCase implements ITecnologiaServicePort {

    private final ITecnologiaPersistencePort tecnologiaPersistencePort;

    public TecnologiaUseCase(ITecnologiaPersistencePort tecnologiaPersistencePort) {
        this.tecnologiaPersistencePort = tecnologiaPersistencePort;
    }

    @Override
    public Mono<Tecnologia> saveTecnologia(Tecnologia tecnologia) {
        return tecnologiaPersistencePort.findByName(tecnologia.getNombre())
                .flatMap(existing -> Mono.<Tecnologia>error(new RuntimeException("El nombre de la Tecnologia ya existe")))
                .switchIfEmpty(Mono.defer(() -> tecnologiaPersistencePort.save(tecnologia)));
    }

    @Override
    public Mono<Tecnologia> getTecnologiaById(Long id) {
        return tecnologiaPersistencePort.getTecnologiaById(id);
    }
}
