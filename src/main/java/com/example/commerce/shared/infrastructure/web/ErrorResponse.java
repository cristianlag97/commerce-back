package com.example.commerce.shared.infrastructure.web;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private Map<String, String> details; // opcional (para validaciones)

    public ErrorResponse(int status, String error, String message) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse(int status, String error, String message, Map<String, String> details) {
        this(status, error, message);
        this.details = details;
    }

    // getters
    public String getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public Map<String, String> getDetails() { return details; }
}