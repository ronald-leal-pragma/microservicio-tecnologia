package com.pragma.tecnologia.tecnologia.infrastructure.endpoints.web;

import com.pragma.tecnologia.application.dtos.requests.TecnologiaRequest;
import com.pragma.tecnologia.application.mappers.TecnologiaMapper;
import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.infrastructure.web.TecnologiaHandler;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TecnologiaHandlerTest {

    @Mock
    private ITecnologiaServicePort tecnologiaServicePort;

    @Mock
    private TecnologiaMapper tecnologiaMapper;

    @Mock
    private Validator validator;

    @InjectMocks
    private TecnologiaHandler tecnologiaHandler;

    @Mock
    private ServerRequest serverRequest;

    @Test
    @DisplayName("Should return Created status when saving valid technology")
    void saveTecnologiaSuccess() {
        // Arrange
        TecnologiaRequest requestDTO = new TecnologiaRequest("Java", "Desc java");
        Tecnologia domainModel = new Tecnologia(null, "Java", "Desc java");
        Tecnologia savedModel = new Tecnologia(1L, "Java", "Desc java");

        when(serverRequest.bodyToMono(TecnologiaRequest.class)).thenReturn(Mono.just(requestDTO));
        when(validator.validate(requestDTO)).thenReturn(Collections.emptySet());
        when(tecnologiaMapper.toDomain(requestDTO)).thenReturn(domainModel);
        when(tecnologiaServicePort.saveTecnologia(domainModel)).thenReturn(Mono.just(savedModel));

        // Act
        Mono<ServerResponse> responseMono = tecnologiaHandler.saveTecnologia(serverRequest);

        // Assert
        StepVerifier.create(responseMono)
                .assertNext(response -> assertEquals(HttpStatus.CREATED, response.statusCode()))
                .verifyComplete();
    }
}
