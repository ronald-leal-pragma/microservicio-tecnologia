package com.pragma.archetypespringboot.user.domain.ports.in;

import com.pragma.archetypespringboot.user.domain.models.RoleModel;

public interface RoleServicePort {
    void save(RoleModel roleModel);

}
