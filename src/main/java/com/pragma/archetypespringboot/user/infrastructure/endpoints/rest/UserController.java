package com.pragma.archetypespringboot.user.infrastructure.endpoints.rest;

import com.pragma.archetypespringboot.user.application.dtos.requests.SaveUserRequest;
import com.pragma.archetypespringboot.user.application.dtos.responses.GenericUserResponse;
import com.pragma.archetypespringboot.user.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<GenericUserResponse> save(@RequestBody SaveUserRequest saveUserRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(saveUserRequest));
    }
    @GetMapping("/get/{idUser}")
    public ResponseEntity<GenericUserResponse> getById(@PathVariable Long idUser) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(idUser));
    }
}
