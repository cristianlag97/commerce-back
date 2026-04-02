package com.example.commerce.users.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entidad JPA que representa la tabla de usuarios.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * ID único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Nombre del usuario.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Email único del usuario.
     *
     * Este campo será clave para login.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Password encriptada.
     *
     * Nunca deberíamos guardar contraseñas en texto plano.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Fecha de creación.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * Relación muchos a muchos:
     * un usuario puede tener varios roles
     * y un rol puede pertenecer a varios usuarios.
     *
     * @JoinTable define la tabla intermedia.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    /**
     * Constructor vacío obligatorio para JPA.
     */
    public UserEntity() {}

    /**
     * Constructor completo.
     */
    public UserEntity(
            UUID id,
            String name,
            String email,
            String password,
            LocalDateTime createdAt,
            Set<RoleEntity> roles
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.roles = roles != null ? roles : new HashSet<>();
    }

    /**
     * Constructor útil para crear un usuario nuevo.
     */
    public UserEntity(
            String name,
            String email,
            String password,
            LocalDateTime createdAt,
            Set<RoleEntity> roles
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.roles = roles != null ? roles : new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Aquí irá luego el password ya encriptado.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}