package com.example.archetypespringboot.user.application.services.impl;

import com.example.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.example.archetypespringboot.user.application.dtos.responses.GenericUserResponse;
import com.example.archetypespringboot.user.application.mappers.UserDtoMapper;
import com.example.archetypespringboot.user.application.services.SaveUserService;
import com.example.archetypespringboot.user.domain.ports.in.SaveUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveUserServiceImpl implements SaveUserService {

    private final SaveUserServicePort saveUserServicePort;
    private final UserDtoMapper userDtoMapper;
    @Override
    public GenericUserResponse saveUser(SaveUserRequest saveUserRequest) {
        return userDtoMapper.modelToResponse(saveUserServicePort.saveUser(userDtoMapper.requestToModel(saveUserRequest)));
    }
}
