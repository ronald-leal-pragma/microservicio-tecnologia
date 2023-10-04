package com.pragma.archetypespringboot.user.application.mappers;

import com.pragma.archetypespringboot.user.application.dtos.requests.RoleRequest;
import com.pragma.archetypespringboot.user.domain.models.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {
    RoleModel requestToModel (RoleRequest roleRequest);

}
