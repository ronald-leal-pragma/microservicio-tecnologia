package com.pragma.tecnologia.tecnologia.domain.usecases;

import com.pragma.tecnologia.domain.models.Tecnologia;
import com.pragma.tecnologia.domain.ports.out.ITecnologiaPersistencePort;
import com.pragma.tecnologia.domain.usecases.TecnologiaUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TecnologiaUseCaseTest {

    @Mock
    private ITecnologiaPersistencePort tecnologiaPersistencePort;

    @InjectMocks
    private TecnologiaUseCase tecnologiaUseCase;

    @Test
    @DisplayName("Should save technology when name does not exist")
    void saveTecnologiaSuccess() {
        // Arrange
        Tecnologia tecnologia = new Tecnologia(null, "Java", "Programming Language");
        Tecnologia savedTecnologia = new Tecnologia(1L, "Java", "Programming Language");

        when(tecnologiaPersistencePort.findByName("Java")).thenReturn(Mono.empty());
        when(tecnologiaPersistencePort.save(tecnologia)).thenReturn(Mono.just(savedTecnologia));

        // Act & Assert
        StepVerifier.create(tecnologiaUseCase.saveTecnologia(tecnologia))
                .expectNext(savedTecnologia)
                .verifyComplete();
        
        verify(tecnologiaPersistencePort).findByName("Java");
        verify(tecnologiaPersistencePort).save(tecnologia);
    }

    @Test
    @DisplayName("Should fail when technology name already exists")
    void saveTecnologiaFailDuplicateName() {
        // Arrange
        Tecnologia tecnologia = new Tecnologia(null, "Java", "Programming Language");
        Tecnologia existingTecnologia = new Tecnologia(1L, "Java", "Existing Desc");

        // Simulate that findByName returns an existing technology
        when(tecnologiaPersistencePort.findByName("Java")).thenReturn(Mono.just(existingTecnologia));

        // Act & Assert
        StepVerifier.create(tecnologiaUseCase.saveTecnologia(tecnologia))
                .expectErrorMatches(throwable -> 
                        throwable instanceof RuntimeException && 
                        throwable.getMessage().equals("El nombre de la Tecnologia ya existe"))
                .verify();

        verify(tecnologiaPersistencePort).findByName("Java");
        verify(tecnologiaPersistencePort, never()).save(any(Tecnologia.class));
    }
}
