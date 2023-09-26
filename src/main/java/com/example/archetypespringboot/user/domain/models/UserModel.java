package com.example.archetypespringboot.user.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private Integer age;
    private String document;
}
