package com.acropolis.bfhl.service;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;

/**
 * Service interface for BFHL API operations.
 */
public interface BfhlService {

    /**
     * Processes the incoming request containing data array, filters elements,
     * and performs custom transformation and aggregation logic.
     *
     * @param request the BfhlRequest containing input data list
     * @return the structured BfhlResponse
     */
    BfhlResponse processInput(BfhlRequest request);
}
