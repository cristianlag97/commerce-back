package com.example.commerce.users.domain.model;

import java.util.UUID;

/**
 * Modelo de dominio que representa un Rol dentro del sistema.
 * Ejemplo: ADMIN, USER
 *
 * Este objeto es PURO dominio:
 * - No tiene anotaciones de JPA
 * - No depende de Spring
 */
public class Role {

    // Identificador único del rol
    private UUID id;

    // Nombre del rol (ej: ADMIN, USER)
    private String name;

    /**
     * Constructor completo.
     * Se usa cuando el objeto ya existe (por ejemplo, viene de base de datos).
     */
    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor para crear un nuevo rol.
     * Aquí no se pasa el id porque normalmente lo genera la base de datos.
     */
    public Role(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}