package com.acropolis.bfhl.exception;

import java.time.LocalDateTime;

/**
 * Standardized Error Response body.
 */
public class ErrorResponse {

    private boolean is_success;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse() {
        this.is_success = false;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message) {
        this.is_success = false;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
