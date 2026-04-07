package com.example.commerce.auth.application.usecase;

import com.example.commerce.auth.application.dto.LoginRequest;
import com.example.commerce.auth.application.dto.LoginResponse;
import com.example.commerce.auth.domain.exception.InvalidCredentialsException;
import com.example.commerce.auth.infrastructure.security.JwtService;
import com.example.commerce.users.domain.model.User;
import com.example.commerce.users.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCase(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse execute(LoginRequest request) {
        User user = userRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));
        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token, "Bearer");
    }
}