package com.example.commerce.auth.infrastructure.security;

import org.springframework.stereotype.Service;

/**
 * Servicio encargado de trabajar con JWT.
 *
 * Por ahora solo se encargará de generar tokens.
 * Más adelante también servirá para:
 * - validar tokens
 * - extraer el email del token
 */
@Service
public class JwtService {

    /**
     * Clave secreta que se usa para firmar el token.
     *
     * La leemos desde application.properties:
     * app.jwt.secret=...
     */
}
