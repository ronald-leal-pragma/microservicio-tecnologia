package com.example.archetypespringboot.user.infrastructure.adapters.persistence;

import com.example.archetypespringboot.user.domain.models.UserModel;
import com.example.archetypespringboot.user.domain.ports.out.UserPersistencePort;
import com.example.archetypespringboot.user.infrastructure.mappers.UserEntityMapper;
import com.example.archetypespringboot.user.infrastructure.repositories.postgresql.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userEntityMapper.entityToModel(userRepository.save(userEntityMapper.modelToEntity(userModel)));
    }

    @Override
    public UserModel getUserById(Long id) {
        return userEntityMapper.entityToModel(userRepository.findById(id).orElse(null));
    }
}
