package com.example.commerce.users.infrastructure.config;

import com.example.commerce.users.infrastructure.persistence.entity.RoleEntity;
import com.example.commerce.users.infrastructure.persistence.repository.JpaRoleRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class RoleDataInitializer implements CommandLineRunner {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleDataInitializer(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public void run(String @NonNull ... args) {
        createRoleIfNotExists("USER");
        createRoleIfNotExists("ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {
        boolean exists = jpaRoleRepository.findByName(roleName).isPresent();

        if (!exists) {
            jpaRoleRepository.save(new RoleEntity(roleName));
        }
    }
}