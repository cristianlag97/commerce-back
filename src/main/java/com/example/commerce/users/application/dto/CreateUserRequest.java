package com.example.commerce.users.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para crear un usuario.
 *
 * DTO = Data Transfer Object
 * Sirve para transportar datos entre capas.
 *
 * Aquí todavía no estamos usando el modelo User directamente,
 * porque en arquitectura limpia/hexagonal conviene que los casos
 * de uso reciban objetos específicos para cada acción.
 */
public class CreateUserRequest {

    // Nombre del usuario a crear
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    // Email del usuario
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    // Password del usuario
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    /**
     * Constructor vacío.
     *
     * Es útil para ciertos frameworks o serializaciones.
     */
    public CreateUserRequest() {
    }

    /**
     * Constructor completo para crear la request manualmente.
     */
    public CreateUserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
}