package com.example.commerce.shared.infrastructure.exception;

// Excepciones de negocio específicas que tú definiste
import com.example.commerce.auth.domain.exception.InvalidCredentialsException;
import com.example.commerce.users.domain.exception.EmailAlreadyExistsException;
import com.example.commerce.users.domain.exception.RoleNotFoundException;

// Clases de Spring para manejar respuestas y validaciones
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Tu clase personalizada para responder errores en formato JSON
import com.example.commerce.shared.infrastructure.web.ErrorResponse;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase global que intercepta excepciones en toda la aplicación.
 *
 * @RestControllerAdvice:
 * Le dice a Spring:
 * "cualquier excepción que ocurra en controllers, pásala por aquí"
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja el caso donde el email ya existe.
     *
     * Se lanza desde el use case:
     * throw new EmailAlreadyExistsException(...)
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        // Construimos el objeto de respuesta con la información del error
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(), //409
                "Conflict",
                ex.getMessage() //mensaje de la excepción
        );
        // Devolvemos la respuesta con status 409 y el cuerpo del error
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Maneja errores de validación de DTOs.
     *
     * Esto ocurre cuando usas @Valid y fallan reglas como:
     * - @NotBlank
     * - @Email
     * - @Size
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        /**
         * Aquí transformamos los errores de validación en un Map:
         *
         * Ejemplo:
         * {
         *   "email": "El email no es válido",
         *   "password": "Debe tener mínimo 6 caracteres"
         * }
         */
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors() // lista de errores por campo
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField, // nombre del campo
                        DefaultMessageSourceResolvable::getDefaultMessage, // mensaje
                        (msg1, msg2) -> msg1 // evita conflictos si hay duplicados
                ));

        // Creamos respuesta con detalles
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), // 400
                "Bad Request",
                "Errores de validación",
                errors // detalles por campo
        );


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Maneja credenciales inválidas (login).
     *
     * Se usa cuando:
     * - email no existe
     * - password no coincide
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    /**
     * Maneja cuando un rol no existe.
     *
     * Esto normalmente es un error interno del sistema,
     * no del usuario.
     */
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * Maneja cualquier excepción no controlada.
     *
     * Este es el "catch global".
     * Si algo no entra en ningún handler anterior,
     * cae aquí.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ocurrió un error inesperado"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * Maneja errores genéricos de argumentos inválidos.
     *
     * Ejemplo:
     * throw new IllegalArgumentException("Algo está mal");
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}