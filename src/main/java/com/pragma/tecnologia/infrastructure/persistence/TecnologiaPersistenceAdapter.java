package com.pragma.tecnologia.infrastructure.persistence;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import com.pragma.tecnologia.infrastructure.mappers.TecnologiaEntityMapper;
import com.pragma.tecnologia.infrastructure.r2dbc.ITecnologiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TecnologiaPersistenceAdapter implements ITecnologiaPersistencePort {

    private final ITecnologiaRepository tecnologiaRepository;
    private final TecnologiaEntityMapper tecnologiaEntityMapper;

    @Override
    public Mono<Tecnologia> save(Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologiaEntityMapper.toEntity(tecnologia))
                .map(tecnologiaEntityMapper::toDomain);
    }

    @Override
    public Mono<Tecnologia> findByName(String nombre) {
        return tecnologiaRepository.findByNombre(nombre)
                .map(tecnologiaEntityMapper::toDomain);
    }
    @Override
    public Mono<Tecnologia> getTecnologiaById(Long id) {
        return tecnologiaRepository.findById(id)
                .map(tecnologiaEntityMapper::toDomain);
    }}
