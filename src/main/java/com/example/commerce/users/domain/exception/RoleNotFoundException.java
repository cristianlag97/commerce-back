package com.example.commerce.users.domain.exception;

/**
 * Excepción de dominio para cuando un rol requerido no existe.
 */
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }
}
