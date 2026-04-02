package com.example.commerce.auth.domain.exception;

/**
 * Excepción de dominio para credenciales inválidas.
 *
 * La usamos cuando:
 * - el email no existe
 * - la contraseña no coincide
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
