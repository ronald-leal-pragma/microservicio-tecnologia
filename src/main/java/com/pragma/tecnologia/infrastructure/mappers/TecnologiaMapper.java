package com.pragma.tecnologia.infrastructure.mappers;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.infrastructure.requests.TecnologiaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TecnologiaMapper {
    Tecnologia toDomain(TecnologiaRequest request);
}
