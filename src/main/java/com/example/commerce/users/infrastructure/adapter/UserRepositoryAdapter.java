package com.example.commerce.users.infrastructure.adapter;

import com.example.commerce.users.domain.model.Role;
import com.example.commerce.users.domain.model.User;
import com.example.commerce.users.domain.port.UserRepositoryPort;
import com.example.commerce.users.infrastructure.persistence.entity.RoleEntity;
import com.example.commerce.users.infrastructure.persistence.entity.UserEntity;
import com.example.commerce.users.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    /**
     * Convierte UserEntity a User del dominio.
     */
    private User toDomain(UserEntity entity) {
        Set<Role> roles = entity.getRoles()
                .stream()
                .map(roleEntity -> new Role(
                        roleEntity.getId(),
                        roleEntity.getName()
                ))
                .collect(Collectors.toSet());

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                roles,
                entity.getCreatedAt()
        );
    }

    private UserEntity toEntity(User user) {
        Set<RoleEntity> roles = user.getRoles()
                .stream()
                .map(role -> new RoleEntity(
                        role.getId(),
                        role.getName()
                ))
                .collect(Collectors.toSet());

        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                roles
        );
    }
}