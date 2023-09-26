package com.example.archetypespringboot.user.application.dtos.requests;

import lombok.Data;

@Data
public class SaveUserRequest {
    private String name;
    private Integer age;
    private String document;
}
