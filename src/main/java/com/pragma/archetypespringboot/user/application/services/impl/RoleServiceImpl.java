package com.pragma.archetypespringboot.user.application.services.impl;

import com.pragma.archetypespringboot.user.application.dtos.requests.RoleRequest;
import com.pragma.archetypespringboot.user.application.mappers.RoleDtoMapper;
import com.pragma.archetypespringboot.user.application.services.RoleService;
import com.pragma.archetypespringboot.user.domain.ports.in.RoleServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleServicePort roleServicePort;
    private final RoleDtoMapper roleDtoMapper;
    @Override
    public void save(RoleRequest roleRequest) {
        roleServicePort.save(roleDtoMapper.requestToModel(roleRequest));
    }
}
