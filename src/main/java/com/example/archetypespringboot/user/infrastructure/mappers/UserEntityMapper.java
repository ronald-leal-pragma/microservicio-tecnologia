package com.example.archetypespringboot.user.infrastructure.mappers;

import com.example.archetypespringboot.user.domain.models.UserModel;
import com.example.archetypespringboot.user.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserModel entityToModel(UserEntity userEntity);

    UserEntity modelToEntity(UserModel userModel);

}
