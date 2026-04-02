package com.example.commerce.users.application.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO de salida para exponer datos del usuario sin información sensible.
 */
public class UserResponse {

    private UUID id;
    private String name;
    private String email;
    private Set<String> roles;
    private LocalDateTime createdAt;

    public UserResponse(
            UUID id,
            String name,
            String email,
            Set<String> roles,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.createdAt = createdAt;
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

    public Set<String> getRoles() {
        return roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}