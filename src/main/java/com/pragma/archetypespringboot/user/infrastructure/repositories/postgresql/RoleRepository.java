package com.pragma.archetypespringboot.user.infrastructure.repositories.postgresql;

import com.pragma.archetypespringboot.user.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
