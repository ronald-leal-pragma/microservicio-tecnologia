package com.example.archetypespringboot.user.application.mappers;

import com.example.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.example.archetypespringboot.user.application.dtos.responses.GenericUserResponse;
import com.example.archetypespringboot.user.domain.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

    GenericUserResponse modelToResponse(UserModel userModel);

    UserModel requestToModel (SaveUserRequest saveUserRequest);

}
