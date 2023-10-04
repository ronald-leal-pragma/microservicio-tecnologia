package com.pragma.archetypespringboot.user.application.services;

import com.pragma.archetypespringboot.user.application.dtos.requests.RoleRequest;

public interface RoleService {
    void save(RoleRequest roleRequest);
}
