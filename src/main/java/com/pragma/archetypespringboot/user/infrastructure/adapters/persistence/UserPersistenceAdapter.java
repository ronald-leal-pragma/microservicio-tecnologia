package com.pragma.archetypespringboot.user.infrastructure.adapters.persistence;

import com.pragma.archetypespringboot.user.domain.models.UserModel;
import com.pragma.archetypespringboot.user.domain.ports.out.UserPersistencePort;
import com.pragma.archetypespringboot.user.infrastructure.mappers.UserEntityMapper;
import com.pragma.archetypespringboot.user.infrastructure.repositories.postgresql.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserModel save(UserModel userModel) {
        return userEntityMapper.entityUserToModel(userRepository.save(userEntityMapper.modelUserToEntity(userModel)));
    }

    @Override
    public UserModel getById(Long idUser) {
        return userEntityMapper.entityUserToModel(userRepository.findById(idUser).orElse(null));
    }

    @Override
    public UserModel getUserByDocument(String document){
        return userEntityMapper.entityUserToModel(userRepository.findUserByDocument(document).orElse(null));
    }

}
