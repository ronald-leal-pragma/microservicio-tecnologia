package com.pragma.tecnologia.application.configurations;

import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import com.pragma.tecnologia.domain.usecases.TecnologiaUseCase;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ITecnologiaServicePort tecnologiaServicePort(ITecnologiaPersistencePort technologyPersistencePort) {
        return new TecnologiaUseCase(technologyPersistencePort);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de tecnologías del Bootcamp")
                        .version("1.0")
                        .description("Servicios reactivos para la gestión de tecnologías, capacidades y franquicias en el Bootcamp"));
    }
}
