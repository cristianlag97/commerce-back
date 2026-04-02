package com.example.commerce.users.infrastructure.config;

import com.example.commerce.users.infrastructure.persistence.entity.RoleEntity;
import com.example.commerce.users.infrastructure.persistence.repository.JpaRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Esta clase se ejecuta automáticamente cuando la aplicación arranca.
 *
 * La usamos para insertar datos iniciales necesarios para el sistema,
 * en este caso los roles base.
 */
@Component
public class RoleDataInitializer implements CommandLineRunner {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleDataInitializer(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    /**
     * Spring ejecuta este método al iniciar la aplicación.
     */
    @Override
    public void run(String... args) {
        createRoleIfNotExists("USER");
        createRoleIfNotExists("ADMIN");
    }

    /**
     * Crea un rol solo si todavía no existe en base de datos.
     */
    private void createRoleIfNotExists(String roleName) {
        boolean exists = jpaRoleRepository.findByName(roleName).isPresent();

        if (!exists) {
            jpaRoleRepository.save(new RoleEntity(roleName));
        }
    }
}