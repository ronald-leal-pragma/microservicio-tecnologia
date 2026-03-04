package com.pragma.tecnologia.tecnologia.infrastructure.endpoints.web;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.in.ITecnologiaServicePort;
import com.pragma.tecnologia.infrastructure.mappers.TecnologiaMapper;
import com.pragma.tecnologia.infrastructure.requests.TecnologiaRequest;
import com.pragma.tecnologia.infrastructure.web.TecnologiaHandler;
import com.pragma.tecnologia.infrastructure.web.TecnologiaRouter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TecnologiaRouter.class, TecnologiaHandler.class})
@WebFluxTest
class TecnologiaRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ITecnologiaServicePort tecnologiaServicePort;

    @MockBean
    private TecnologiaMapper tecnologiaMapper;

    @Test
    @DisplayName("Router should route POST /api/tecnologia to handler")
    void routeToSaveTecnologia() {
        // Arrange
        TecnologiaRequest requestDTO = new TecnologiaRequest("Java", "Desc");
        Tecnologia domainModel = new Tecnologia(null, "Java", "Desc");
        Tecnologia savedModel = new Tecnologia(1L, "Java", "Desc");

        when(tecnologiaMapper.toDomain(any())).thenReturn(domainModel);
        when(tecnologiaServicePort.saveTecnologia(any())).thenReturn(Mono.just(savedModel));

        // Act & Assert
        webTestClient.post()
                .uri("/api/tecnologia")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.nombre").isEqualTo("Java");
    }
}
