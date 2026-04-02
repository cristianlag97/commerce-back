package com.example.commerce.auth.infrastructure.web;

import com.example.commerce.auth.application.dto.LoginRequest;
import com.example.commerce.auth.application.dto.LoginResponse;
import com.example.commerce.auth.application.usecase.LoginUseCase;
import com.example.commerce.users.application.dto.CreateUserRequest;
import com.example.commerce.users.application.dto.UserResponse;
import com.example.commerce.users.application.usecase.CreateUserUseCase;
import com.example.commerce.users.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Controller de autenticación.
 *
 * Por ahora solo manejará el registro.
 * Más adelante también tendrá login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(CreateUserUseCase createUserUseCase, LoginUseCase loginUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    /**
     * Endpoint para registrar usuarios.
     *
     * POST /api/auth/register
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody CreateUserRequest request) {
        return createUserUseCase.execute(request);
    }

    /**
     * Endpoint para iniciar sesión.
     *
     * POST /api/auth/login
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return loginUseCase.execute(request);
    }
}