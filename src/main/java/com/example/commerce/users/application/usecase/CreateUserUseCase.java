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

@Service
public class CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
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

    public UserResponse execute(CreateUserRequest request) {

        if (userRepositoryPort.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("El email ya está registrado");
        }

        Role userRole = roleRepositoryPort.findByName("USER")
                .orElseThrow(() -> new RoleNotFoundException("El rol USER no existe"));

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword,
                Set.of(userRole)
        );

        User savedUser = userRepositoryPort.save(user);
        return toResponse(savedUser);
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