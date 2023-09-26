package com.example.archetypespringboot.user.infrastructure.endpoints.rest;

import com.example.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.example.archetypespringboot.user.application.dtos.responses.GenericUserResponse;
import com.example.archetypespringboot.user.application.services.SaveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/save-user")
@RequiredArgsConstructor
public class SaveUserController {

    private final SaveUserService saveUserService;

    @PostMapping("/")
    public ResponseEntity<GenericUserResponse> saveUser(@RequestBody SaveUserRequest saveUserRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUserService.saveUser(saveUserRequest));
    }

}
