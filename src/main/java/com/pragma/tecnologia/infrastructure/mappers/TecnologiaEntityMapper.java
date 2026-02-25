package com.pragma.tecnologia.infrastructure.mappers;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.infrastructure.entities.TecnologiaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TecnologiaEntityMapper {
    Tecnologia toDomain(TecnologiaEntity entity);
    TecnologiaEntity toEntity(Tecnologia domain);
}
