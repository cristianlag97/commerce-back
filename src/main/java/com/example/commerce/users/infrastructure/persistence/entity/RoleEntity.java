package com.example.commerce.users.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * Entidad JPA para la tabla de roles.
 *
 * Esta clase sí pertenece a infraestructura porque:
 * - usa anotaciones JPA
 * - representa cómo se guarda en base de datos
 */
@Entity
@Table(name = "roles")
public class RoleEntity {
    /**
     * ID único del rol.
     *
     * Se genera automáticamente usando UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Nombre del rol.
     *
     * unique = true -> no puede haber dos roles con el mismo nombre
     * nullable = false -> no puede ser null
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Constructor vacío obligatorio para JPA.
     *
     * Hibernate lo necesita para poder instanciar la entidad.
     */
    public RoleEntity() {}

    /**
     * Constructor útil cuando queremos crear una entidad nueva manualmente.
     */
    public RoleEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    /**
     * No siempre es obligatorio tener setter del id,
     * pero lo dejamos por compatibilidad y flexibilidad.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
