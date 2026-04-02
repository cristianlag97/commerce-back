package com.example.commerce.auth.application.usecase;

import com.example.commerce.auth.application.dto.LoginRequest;
import com.example.commerce.auth.application.dto.LoginResponse;
import com.example.commerce.auth.domain.exception.InvalidCredentialsException;
import com.example.commerce.users.domain.model.Role;
import com.example.commerce.users.domain.model.User;
import com.example.commerce.users.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Caso de uso para autenticar usuarios.
 *
 * Esta clase se encarga de:
 * 1. buscar el usuario por email
 * 2. validar la contraseña
 * 3. devolver una respuesta de login exitoso
 */

@Service
public class LoginUseCase {

    /**
     * Puerto del dominio para consultar usuarios.
     *
     * Ojo:
     * Aquí usamos el puerto, no JpaUserRepository directamente,
     * para respetar la arquitectura hexagonal.
     */
    private final UserRepositoryPort userRepositoryPort;

    /**
     * Componente de Spring Security para verificar contraseñas.
     *
     * No compara texto plano con texto plano.
     * Compara la contraseña enviada con el hash almacenado.
     */
    private final PasswordEncoder passwordEncoder;

    public LoginUseCase(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Ejecuta el flujo de login.
     */

    public LoginResponse execute(LoginRequest request) {

        /**
         * Paso 1:
         * Buscar el usuario por email.
         *
         * Si no existe, lanzamos error.
         */
        User user = userRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));

        /**
         * Paso 2:
         * Comparar la contraseña enviada contra la contraseña hasheada guardada.
         *
         * request.getPassword()      -> contraseña que escribe el usuario
         * user.getPassword()         -> hash guardado en base de datos
         */
        boolean passwordMatches= passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        /**
         * Si no coincide, lanzamos error.
         */
        if (!passwordMatches) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        /**
         * Paso 3:
         * Devolver una respuesta temporal de éxito.
         *
         * Más adelante aquí devolveremos también el JWT.
         */
        return new LoginResponse(
                "Login exitoso",
                user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }
}
