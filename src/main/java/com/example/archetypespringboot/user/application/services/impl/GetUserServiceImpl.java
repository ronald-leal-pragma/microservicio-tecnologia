package com.example.archetypespringboot.user.application.services.impl;

import com.example.archetypespringboot.user.application.dtos.responses.GenericUserResponse;
import com.example.archetypespringboot.user.application.mappers.UserDtoMapper;
import com.example.archetypespringboot.user.application.services.GetUserService;
import com.example.archetypespringboot.user.domain.ports.in.GetUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService {

    private final GetUserServicePort getUserServicePort;
    private final UserDtoMapper userDtoMapper;
    @Override
    public GenericUserResponse getUserById(Long id) {
        return userDtoMapper.modelToResponse(getUserServicePort.getUserById(id));
    }
}
