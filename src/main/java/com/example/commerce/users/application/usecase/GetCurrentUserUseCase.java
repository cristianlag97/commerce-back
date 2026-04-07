package com.example.commerce.users.application.usecase;

import com.example.commerce.users.application.dto.UserResponse;
import com.example.commerce.users.domain.exception.UserNotFoundException;
import com.example.commerce.users.domain.model.Role;
import com.example.commerce.users.domain.model.User;
import com.example.commerce.users.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GetCurrentUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetCurrentUserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public UserResponse execute(String email) {

        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()),
                user.getCreatedAt()
        );
    }
}