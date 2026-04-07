package com.example.commerce.users.infrastructure.adapter;

import com.example.commerce.users.domain.model.Role;
import com.example.commerce.users.domain.port.RoleRepositoryPort;
import com.example.commerce.users.infrastructure.persistence.entity.RoleEntity;
import com.example.commerce.users.infrastructure.persistence.repository.JpaRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = toEntity(role);
        RoleEntity savedEntity = jpaRoleRepository.save(roleEntity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return jpaRoleRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByName(name)
                .map(this::toDomain);
    }

    private Role toDomain(RoleEntity entity) {
        return new Role(
                entity.getId(),
                entity.getName()
        );
    }

    private RoleEntity toEntity(Role role) {
        return new RoleEntity(
                role.getId(),
                role.getName()
        );
    }
}