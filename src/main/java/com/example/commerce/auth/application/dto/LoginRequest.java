package com.example.commerce.auth.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para el login.
 *
 * Aquí definimos los datos que el cliente debe enviar
 * para autenticarse.
 */
public class LoginRequest {

    /**
     * Email del usuario que intenta iniciar sesión.
     *
     * @NotBlank -> no permite null ni texto vacío
     * @Email -> valida formato de correo
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    /**
     * Contraseña enviada por el usuario.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    /**
     * Constructor vacío requerido por Spring
     * para convertir JSON a objeto Java.
     */
    public LoginRequest() {
    }

    /**
     * Constructor útil para pruebas o creación manual.
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
