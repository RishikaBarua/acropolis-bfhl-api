package com.acropolis.bfhl.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Request Data Transfer Object for BFHL REST API.
 */
public class BfhlRequest {

    @NotNull(message = "Data array cannot be null")
    private List<String> data;

    // Default Constructor
    public BfhlRequest() {
    }

    // All-args Constructor
    public BfhlRequest(List<String> data) {
        this.data = data;
    }

    // Getter and Setter
    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
