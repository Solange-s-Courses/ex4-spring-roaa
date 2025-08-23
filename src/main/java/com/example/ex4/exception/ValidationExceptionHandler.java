package com.example.ex4.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for validation-related errors in REST controllers.
 *
 * Handles:
 * <ul>
 *   <li>Bean validation errors via {@link MethodArgumentNotValidException}</li>
 *   <li>Constraint violations via {@link ConstraintViolationException}</li>
 *   <li>Fallback for other uncaught exceptions</li>
 * </ul>
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Handles validation errors triggered by @Valid on @RequestBody parameters.
     *
     * @param ex the exception thrown when validation fails
     * @return a map of field names to error messages with HTTP 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles constraint violations typically thrown from @Validated on path/query parameters.
     *
     * @param ex the constraint violation exception
     * @return a simple error message with HTTP 400 status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
    }

    /**
     * Handles all other uncaught exceptions in the application.
     *
     * @param ex the exception thrown
     * @return a general error message with HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal error: " + ex.getMessage());
    }
}
