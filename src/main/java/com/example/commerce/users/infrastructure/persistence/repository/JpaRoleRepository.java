package com.example.commerce.users.infrastructure.persistence.repository;

import com.example.commerce.users.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio JPA para RoleEntity.
 */
public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {

    /**
     * Buscar rol por nombre (ej: USER, ADMIN)
     */
    Optional<RoleEntity> findByName(String name);
}