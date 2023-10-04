package com.pragma.archetypespringboot.user.domain.ports.out;

import com.pragma.archetypespringboot.user.domain.models.RoleModel;

public interface RolePersistencePort {
    void save(RoleModel roleModel);
}
