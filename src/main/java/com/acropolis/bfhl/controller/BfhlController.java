package com.acropolis.bfhl.controller;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import com.acropolis.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle REST API requests for BFHL.
 */
@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    // Constructor Injection (Best Practice)
    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST Endpoint /bfhl.
     * Takes a list of strings, processes it, and returns the sorted, transformed elements.
     * Expected successful HTTP status: 200
     *
     * @param request the validated BfhlRequest body
     * @return ResponseEntity containing BfhlResponse and status 200 OK
     */
    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processInput(request);
        return ResponseEntity.ok(response);
    }
}
