package com.example.commerce.users.infrastructure.web;

import com.example.commerce.users.application.dto.UserResponse;
import com.example.commerce.users.application.usecase.GetCurrentUserUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetCurrentUserUseCase getCurrentUserUseCase;

    public UserController(GetCurrentUserUseCase getCurrentUserUseCase) {
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return getCurrentUserUseCase.execute(email);
    }
}