package com.example.archetypespringboot.user.domain.ports.out;

import com.example.archetypespringboot.user.domain.models.UserModel;

public interface UserPersistencePort {
    UserModel saveUser(UserModel userModel);
    UserModel getUserById(Long id);
}
