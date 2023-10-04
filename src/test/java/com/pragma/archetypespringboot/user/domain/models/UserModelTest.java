package com.pragma.archetypespringboot.user.domain.models;

import com.pragma.archetypespringboot.user.domain.exceptions.InvalidUserParameterException;
import com.pragma.archetypespringboot.user.domain.exceptions.MinorUserException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {


    @Test
    void testValidUserModel() {
        Long id = 1L;
        String name = "Alex Zapata";
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String document = "1234567";

        UserModel user = new UserModel(id, name, birthDate, document);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(document, user.getDocument());
    }

    @Test
    void testMinorUser() {
        Long id = 3L;
        String name = "Alex Zapata";
        LocalDate birthDate = LocalDate.of(2008, 8, 20);
        String document = "5555555";

        assertThrows(MinorUserException.class, () -> {
            new UserModel(id, name, birthDate, document);
        });
    }

    @Test
    void testInvalidDocument() {
        Long id = 4L;
        String name = "Alex Zapata";
        LocalDate birthDate = LocalDate.of(1985, 12, 10);
        String invalidDocument = "abcdefg";

        assertThrows(InvalidUserParameterException.class, () -> {
            new UserModel(id, name, birthDate, invalidDocument);
        });
    }

}