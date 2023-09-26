package com.example.archetypespringboot.user.domain.usecases;

import com.example.archetypespringboot.user.domain.exceptions.UserNotFoundException;
import com.example.archetypespringboot.user.domain.models.UserModel;
import com.example.archetypespringboot.user.domain.ports.in.GetUserServicePort;
import com.example.archetypespringboot.user.domain.ports.in.SaveUserServicePort;
import com.example.archetypespringboot.user.domain.ports.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserUseCase implements GetUserServicePort {

    private final UserPersistencePort userPersistencePort;

    @Override
    public UserModel getUserById(Long id) {
        UserModel userModel = userPersistencePort.getUserById(id);
        if (userModel == null) throw new UserNotFoundException();
        return userModel;
    }
}
