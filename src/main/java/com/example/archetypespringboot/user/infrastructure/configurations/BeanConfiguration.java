package com.example.archetypespringboot.user.infrastructure.configurations;

import com.example.archetypespringboot.user.domain.ports.in.GetUserServicePort;
import com.example.archetypespringboot.user.domain.ports.in.SaveUserServicePort;
import com.example.archetypespringboot.user.domain.ports.out.UserPersistencePort;
import com.example.archetypespringboot.user.domain.usecases.GetUserUseCase;
import com.example.archetypespringboot.user.domain.usecases.SaveUserUseCase;
import com.example.archetypespringboot.user.infrastructure.adapters.persistence.UserPersistenceAdapter;
import com.example.archetypespringboot.user.infrastructure.mappers.UserEntityMapper;
import com.example.archetypespringboot.user.infrastructure.repositories.postgresql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Bean
    public UserPersistencePort userPersistencePort(){
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public SaveUserServicePort saveUserServicePort(){return new SaveUserUseCase(userPersistencePort());
    }
    @Bean
    public GetUserServicePort getUserServicePort(){return new GetUserUseCase(userPersistencePort());
    }
}
