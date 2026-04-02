package com.example.commerce.users.application.usecase;

import com.example.commerce.users.application.dto.CreateUserRequest;
import com.example.commerce.users.application.dto.UserResponse;
import com.example.commerce.users.domain.exception.EmailAlreadyExistsException;
import com.example.commerce.users.domain.exception.RoleNotFoundException;
import com.example.commerce.users.domain.model.Role;
import com.example.commerce.users.domain.model.User;
import com.example.commerce.users.domain.port.RoleRepositoryPort;
import com.example.commerce.users.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Caso de uso para crear un usuario.
 *
 * Aquí vive la lógica de aplicación:
 * - validar reglas del flujo
 * - consultar puertos del dominio
 * - construir el objeto de dominio
 * - persistirlo
 *
 * Ojo:
 * No usamos JPA directamente aquí.
 * Solo usamos puertos.
 */
@Service
public class CreateUserUseCase {

    // Puerto para trabajar con usuarios
    private final UserRepositoryPort userRepositoryPort;

    // Puerto para consultar roles
    private final RoleRepositoryPort roleRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(
            UserRepositoryPort userRepositoryPort,
            RoleRepositoryPort roleRepositoryPort,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.roleRepositoryPort = roleRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Ejecuta el caso de uso de creación de usuario.
     */
    public UserResponse execute(CreateUserRequest request) {

        // Regla 1: validar que el email no exista
        if (userRepositoryPort.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("El email ya está registrado");
        }

        // Buscar el rol por defecto que tendrá el usuario nuevo
        Role userRole = roleRepositoryPort.findByName("USER")
                .orElseThrow(() -> new RoleNotFoundException("El rol USER no existe"));

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Crear el modelo de dominio User
        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword,
                Set.of(userRole)
        );
        // Guardar el usuario y devolverlo
        User savedUser = userRepositoryPort.save(user);
        return toResponse(savedUser);
    }

    /**
     * Convierte el modelo de dominio User
     * en un DTO seguro para responder al cliente.
     */

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toSet()),
                user.getCreatedAt()
        );
    }
}