package com.example.commerce.auth.infrastructure.web;

import com.example.commerce.auth.application.dto.LoginRequest;
import com.example.commerce.auth.application.dto.LoginResponse;
import com.example.commerce.auth.application.usecase.LoginUseCase;
import com.example.commerce.users.application.dto.CreateUserRequest;
import com.example.commerce.users.application.dto.UserResponse;
import com.example.commerce.users.application.usecase.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(CreateUserUseCase createUserUseCase, LoginUseCase loginUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody CreateUserRequest request) {
        return createUserUseCase.execute(request);
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return loginUseCase.execute(request);
    }
}