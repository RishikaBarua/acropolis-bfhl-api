package com.acropolis.bfhl.controller;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import com.acropolis.bfhl.service.BfhlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller-level tests for BFHL REST Endpoint.
 */
@WebMvcTest(BfhlController.class)
public class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BfhlService bfhlService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostBfhlSuccess() throws Exception {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334"));
        
        BfhlResponse mockResponse = new BfhlResponse(
                true,
                "rishika_barua_26052026",
                "rishikabarua230031@acropolis.in",
                "0827CS231216",
                Arrays.asList("1"),
                Arrays.asList("334"),
                Arrays.asList("A"),
                Collections.emptyList(),
                "335",
                "A"
        );

        Mockito.when(bfhlService.processInput(Mockito.any(BfhlRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("rishika_barua_26052026"))
                .andExpect(jsonPath("$.email").value("rishikabarua230031@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827CS231216"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.sum").value("335"))
                .andExpect(jsonPath("$.concat_string").value("A"));
    }

    @Test
    public void testPostBfhlValidationFailure() throws Exception {
        BfhlRequest request = new BfhlRequest(null); // Invalid request: null data

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Validation failed")));
    }
}
