package com.example.commerce.users.infrastructure.web;

import com.example.commerce.users.application.dto.CreateUserRequest;
import com.example.commerce.users.application.dto.UserResponse;
import com.example.commerce.users.application.usecase.CreateUserUseCase;
import com.example.commerce.users.domain.model.Role;
import com.example.commerce.users.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Controller REST para manejar endpoints de usuarios.
 *
 * Este es un ADAPTER DE ENTRADA (inbound adapter).
 * Traduce HTTP → UseCase
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }
}