package com.example.commerce.users.domain.port;

import com.example.commerce.users.domain.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida (OUTBOUND PORT)
 *
 * Define lo que el dominio necesita para persistir usuarios,
 * pero NO cómo se implementa.
 *
 * La implementación real estará en infrastructure.
 */
public interface UserRepositoryPort {

    // Guarda un usuario
    User save(User user);

    // Busca por ID
    Optional<User> findById(UUID id);

    // Busca por email (clave para login)
    Optional<User> findByEmail(String email);

    // Verifica si ya existe (para evitar duplicados)
    boolean existsByEmail(String email);
}