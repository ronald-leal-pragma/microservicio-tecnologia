package com.pragma.archetypespringboot.user.domain.ports.out;

import com.pragma.archetypespringboot.user.domain.models.UserModel;

public interface UserPersistencePort {
    UserModel save(UserModel userModel);
    UserModel getById(Long id);

    UserModel getUserByDocument(String document);

}
