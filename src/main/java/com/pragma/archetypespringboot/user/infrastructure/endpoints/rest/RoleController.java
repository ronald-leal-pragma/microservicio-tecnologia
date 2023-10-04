package com.pragma.archetypespringboot.user.infrastructure.endpoints.rest;

import com.pragma.archetypespringboot.user.application.dtos.requests.RoleRequest;
import com.pragma.archetypespringboot.user.application.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/save-role")
    public ResponseEntity<Void> save(@RequestBody RoleRequest roleRequest){
        roleService.save(roleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
