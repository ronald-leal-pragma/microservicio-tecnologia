package com.pragma.archetypespringboot.user.infrastructure.configurations.traceability;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Getter
public class ParametersApplicationConfiguration {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${spring.platform.name}")
    private String applicationName;
}
