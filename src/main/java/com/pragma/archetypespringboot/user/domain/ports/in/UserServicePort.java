package com.pragma.archetypespringboot.user.domain.ports.in;

import com.pragma.archetypespringboot.user.domain.models.UserModel;

public interface UserServicePort {
    UserModel save(UserModel userModel);

    UserModel getById(Long id);

}
