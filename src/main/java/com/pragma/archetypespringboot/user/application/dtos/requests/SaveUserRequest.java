package com.pragma.archetypespringboot.user.application.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveUserRequest {

    private String name;

    private LocalDate birthDate;

    private String document;
}
