package com.example.archetypespringboot.user.application.services;

import com.example.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.example.archetypespringboot.user.application.dtos.responses.GenericUserResponse;

public interface SaveUserService {
    GenericUserResponse saveUser(SaveUserRequest saveUserRequest);
}
