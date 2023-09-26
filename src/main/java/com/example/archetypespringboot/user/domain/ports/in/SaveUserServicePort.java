package com.example.archetypespringboot.user.domain.ports.in;

import com.example.archetypespringboot.user.domain.models.UserModel;

public interface SaveUserServicePort {
    UserModel saveUser(UserModel userModel);
}
