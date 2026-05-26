package com.acropolis.bfhl.service;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BfhlServiceImpl.
 */
public class BfhlServiceTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    public void setUp() {
        bfhlService = new BfhlServiceImpl();
        // Inject properties that would normally come from application.properties
        ReflectionTestUtils.setField(bfhlService, "fullName", "rishika_barua");
        ReflectionTestUtils.setField(bfhlService, "dob", "26052026");
        ReflectionTestUtils.setField(bfhlService, "email", "rishikabarua230031@acropolis.in");
        ReflectionTestUtils.setField(bfhlService, "rollNumber", "0827CS231216");
    }

    @Test
    public void testExampleA() {
        // Request: ["a", "1", "334", "4", "R", "$"]
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = bfhlService.processInput(request);

        assertTrue(response.isIs_success());
        assertEquals("rishika_barua_26052026", response.getUser_id());
        assertEquals("rishikabarua230031@acropolis.in", response.getEmail());
        assertEquals("0827CS231216", response.getRoll_number());

        assertEquals(Arrays.asList("1"), response.getOdd_numbers());
        assertEquals(Arrays.asList("334", "4"), response.getEven_numbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Arrays.asList("$"), response.getSpecial_characters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcat_string());
    }

    @Test
    public void testExampleB() {
        // Request: ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
        BfhlRequest request = new BfhlRequest(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponse response = bfhlService.processInput(request);

        assertTrue(response.isIs_success());
        assertEquals(Arrays.asList("5"), response.getOdd_numbers());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEven_numbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecial_characters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcat_string());
    }

    @Test
    public void testExampleC() {
        // Request: ["A", "ABCD", "DOE"]
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = bfhlService.processInput(request);

        assertTrue(response.isIs_success());
        assertTrue(response.getOdd_numbers().isEmpty());
        assertTrue(response.getEven_numbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecial_characters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcat_string());
    }

    @Test
    public void testEmptyInput() {
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = bfhlService.processInput(request);

        assertTrue(response.isIs_success());
        assertTrue(response.getOdd_numbers().isEmpty());
        assertTrue(response.getEven_numbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecial_characters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcat_string());
    }

    @Test
    public void testNullValuesInList() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("1", null, "a", null, "$"));
        BfhlResponse response = bfhlService.processInput(request);

        assertTrue(response.isIs_success());
        assertEquals(Arrays.asList("1"), response.getOdd_numbers());
        assertEquals(Arrays.asList("A"), response.getAlphabets());
        assertEquals(Arrays.asList("$"), response.getSpecial_characters());
        assertEquals("1", response.getSum());
        assertEquals("A", response.getConcat_string());
    }
}
