package com.example.commerce.users.domain.port;

import com.example.commerce.users.domain.model.Role;

import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para roles.
 */
public interface RoleRepositoryPort {

    Role save(Role role);

    Optional<Role> findById(UUID id);

    // Muy importante para asignar roles como "USER" o "ADMIN"
    Optional<Role> findByName(String name);
}