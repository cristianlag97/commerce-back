package com.example.commerce.auth.application.dto;

import java.util.Set;

/**
 * DTO de salida temporal para login.
 *
 * Más adelante aquí pondremos también el token JWT.
 */
public class LoginResponse {

    private String message;
    private String email;
    private Set<String> roles;

    public LoginResponse(String message, String email, Set<String> roles) {
        this.message = message;
        this.email = email;
        this.roles = roles;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
