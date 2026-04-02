package com.example.commerce.users.infrastructure.persistence.repository;

import com.example.commerce.users.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio JPA para UserEntity.
 *
 * Spring genera la implementación automáticamente.
 */
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Spring Data JPA genera automáticamente la query:
     * SELECT * FROM users WHERE email = ?
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Verifica si existe un usuario con ese email.
     */
    boolean existsByEmail(String email);
}