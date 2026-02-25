package com.pragma.tecnologia.infrastructure.configurations;

import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import com.pragma.tecnologia.domain.usecases.TecnologiaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ITecnologiaServicePort tecnologiaServicePort(ITecnologiaPersistencePort technologyPersistencePort) {
        return new TecnologiaUseCase(technologyPersistencePort);
    }
}
