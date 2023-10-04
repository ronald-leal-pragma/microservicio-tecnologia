package com.pragma.archetypespringboot.user.domain.models;

import com.pragma.archetypespringboot.user.domain.exceptions.InvalidUserParameterException;
import com.pragma.archetypespringboot.user.domain.exceptions.MinorUserException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
@Getter
@NoArgsConstructor
public class  UserModel {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String document;

    public UserModel(Long id, String name, LocalDate birthDate, String document) {

        if (Period.between(birthDate,LocalDate.now()).getYears()<18) throw new MinorUserException();

        if (!document.matches("\\d{7,15}")) {
            throw new InvalidUserParameterException();
        }

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.document = document;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (Period.between(birthDate,LocalDate.now()).getYears()<18) throw new MinorUserException();
        this.birthDate = birthDate;
    }

    public void setDocument(String document) {
        if (!document.matches("\\d{7,15}")) {
            throw new InvalidUserParameterException();
        }
        this.document = document;
    }
}
