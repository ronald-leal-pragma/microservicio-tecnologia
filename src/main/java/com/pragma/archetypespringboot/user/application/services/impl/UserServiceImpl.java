package com.pragma.archetypespringboot.user.application.services.impl;

import com.pragma.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.pragma.archetypespringboot.user.application.dtos.responses.GenericUserResponse;
import com.pragma.archetypespringboot.user.application.mappers.UserDtoMapper;
import com.pragma.archetypespringboot.user.application.services.UserService;
import com.pragma.archetypespringboot.user.domain.ports.in.UserServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;
    @Override
    public GenericUserResponse save(SaveUserRequest saveUserRequest) {
        return userDtoMapper.modelUserToResponse(userServicePort.save(userDtoMapper.requestToModel(saveUserRequest)));
    }
    @Override
    public GenericUserResponse getById(Long id) {
        return userDtoMapper.modelUserToResponse(userServicePort.getById(id));
    }
}
