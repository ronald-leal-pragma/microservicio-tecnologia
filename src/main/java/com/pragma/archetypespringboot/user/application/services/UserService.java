package com.pragma.archetypespringboot.user.application.services;

import com.pragma.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.pragma.archetypespringboot.user.application.dtos.responses.GenericUserResponse;

public interface UserService {
    GenericUserResponse save(SaveUserRequest saveUserRequest);
    GenericUserResponse getById(Long id);
}
