package com.pragma.archetypespringboot.user.infrastructure.repositories.postgresql;

import com.pragma.archetypespringboot.user.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

  Optional<UserEntity> findUserByDocument(String document);

}
