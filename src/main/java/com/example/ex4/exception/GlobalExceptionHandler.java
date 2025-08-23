package com.example.ex4.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling uncaught exceptions in the application.
 * This class provides a fallback mechanism for rendering a generic error page
 * when an unhandled exception occurs anywhere in the controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all uncaught exceptions (generic fallback).
     *
     * @return the name of the view to display for a 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneric() {
        return "error/500";
    }
}

