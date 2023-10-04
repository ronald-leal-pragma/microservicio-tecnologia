package com.pragma.archetypespringboot.user.domain.usecases;

import com.pragma.archetypespringboot.user.domain.exceptions.UserAlreadyExistsException;
import com.pragma.archetypespringboot.user.domain.exceptions.UserNotFoundException;
import com.pragma.archetypespringboot.user.domain.models.UserModel;
import com.pragma.archetypespringboot.user.domain.ports.out.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;
    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_NewUser() {

        when(userPersistencePort.getUserByDocument(anyString())).thenReturn(null);

        UserModel userModelToSave = new UserModel(1L, "John Doe", LocalDate.of(1990, 1, 1), "123456789");

        userUseCase.save(userModelToSave);

        verify(userPersistencePort, times(1)).save(userModelToSave);
    }

    @Test
    void testSave_UserAlreadyExists() {
        when(userPersistencePort.getUserByDocument(anyString())).thenReturn(new UserModel());

        UserModel userModelToSave = new UserModel(1L, "John Doe", LocalDate.of(1990, 1, 1), "123456789");

        assertThrows(UserAlreadyExistsException.class, () -> {
            userUseCase.save(userModelToSave);
        });

        verify(userPersistencePort, never()).save(any());
    }

    @Test
    void testGetById_UserExists() {
        Long userId = 1L;
        UserModel expectedUser = new UserModel(userId, "John Doe", LocalDate.of(1990, 1, 1), "123456789");

        when(userPersistencePort.getById(userId)).thenReturn(expectedUser);

        UserModel retrievedUser = userUseCase.getById(userId);

        assertSame(expectedUser, retrievedUser);
    }

    @Test
    void testGetById_UserNotFound() {
        Long userId = 1L;

        when(userPersistencePort.getById(userId)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.getById(userId);
        });
    }

}