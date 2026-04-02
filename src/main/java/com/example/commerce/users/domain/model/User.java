package com.example.commerce.users.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Modelo de dominio para el Usuario.
 *
 * Este es el core del negocio:
 * - No depende de Spring
 * - No tiene anotaciones de persistencia
 */
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;

    // Un usuario puede tener múltiples roles
    private Set<Role> roles;

    private LocalDateTime createdAt;

    /**
     * Constructor completo.
     * Se usa cuando el usuario ya existe (por ejemplo, al traerlo de la BD).
     */
    public User(
            UUID id,
            String name,
            String email,
            String password,
            Set<Role> roles,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

        // Evita null, siempre inicializa colección
        this.roles = roles != null ? roles : new HashSet<>();

        this.createdAt = createdAt;
    }

    /**
     * Constructor para crear un usuario nuevo.
     */
    public User(
            String name,
            String email,
            String password,
            Set<Role> roles
    ) {
        this.name = name;
        this.email = email;
        this.password = password;

        this.roles = roles != null ? roles : new HashSet<>();

        // Se define automáticamente la fecha de creación
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}