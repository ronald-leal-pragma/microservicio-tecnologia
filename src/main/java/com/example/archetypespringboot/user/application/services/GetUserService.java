package com.example.archetypespringboot.user.application.services;

import com.example.archetypespringboot.user.application.dtos.responses.GenericUserResponse;

public interface GetUserService {
    GenericUserResponse getUserById(Long id);

}
