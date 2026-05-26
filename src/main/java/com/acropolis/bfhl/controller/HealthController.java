package com.acropolis.bfhl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to handle standard GET /health check queries.
 */
@RestController
public class HealthController {

    /**
     * GET Endpoint /health.
     * Used by monitoring services and deployment platforms to check app status.
     *
     * @return ResponseEntity with status 200 OK and JSON body {"status": "UP"}
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> checkHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}
