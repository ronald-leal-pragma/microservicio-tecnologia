package com.example.archetypespringboot.user.domain.usecases;

import com.example.archetypespringboot.user.domain.models.UserModel;
import com.example.archetypespringboot.user.domain.ports.in.SaveUserServicePort;
import com.example.archetypespringboot.user.domain.ports.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveUserUseCase implements SaveUserServicePort {

    private final UserPersistencePort userPersistencePort;

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userPersistencePort.saveUser(userModel);
    }

}
