package com.acropolis.bfhl.service;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for processing BFHL API requests.
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${user.fullname}")
    private String fullName;

    @Value("${user.dob}")
    private String dob;

    @Value("${user.email}")
    private String email;

    @Value("${user.rollnumber}")
    private String rollNumber;

    @Override
    public BfhlResponse processInput(BfhlRequest request) {
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        
        long sum = 0;
        StringBuilder alphabetBuffer = new StringBuilder();

        List<String> inputData = request.getData();
        if (inputData != null) {
            for (String item : inputData) {
                if (item == null) {
                    continue;
                }
                
                // Trim to remove leading/trailing whitespace
                String trimmedItem = item.trim();

                // 1. Check if the item is a number (integer)
                if (trimmedItem.matches("^-?\\d+$")) {
                    try {
                        long number = Long.parseLong(trimmedItem);
                        sum += number;
                        
                        if (number % 2 == 0) {
                            evenNumbers.add(trimmedItem);
                        } else {
                            oddNumbers.add(trimmedItem);
                        }
                    } catch (NumberFormatException e) {
                        // In case of extreme overflow, treat as special character
                        specialCharacters.add(item);
                    }
                } 
                // 2. Check if the item is purely alphabetical characters
                else if (trimmedItem.matches("^[a-zA-Z]+$")) {
                    // Convert alphabet items to uppercase in the list
                    alphabets.add(trimmedItem.toUpperCase());
                    // Keep original characters for the concat string buffer
                    alphabetBuffer.append(trimmedItem);
                } 
                // 3. Otherwise, it is a special character
                else {
                    specialCharacters.add(item);
                }
            }
        }

        // Generate reverse alternating-caps string
        String concatString = generateAlternatingCapsReverse(alphabetBuffer.toString());

        // Construct User ID: full_name_ddmmyyyy in lowercase
        String userId = (fullName + "_" + dob).toLowerCase();

        // Build Response
        return new BfhlResponse(
                true,
                userId,
                email,
                rollNumber,
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                String.valueOf(sum),
                concatString
        );
    }

    /**
     * Reverses the input string and applies alternating capitalization:
     * Even indices (0, 2, 4...) are UPPERCASE, odd indices (1, 3, 5...) are lowercase.
     */
    private String generateAlternatingCapsReverse(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        // 1. Reverse the string
        String reversed = new StringBuilder(input).reverse().toString();
        
        // 2. Apply alternating caps
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char ch = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        
        return result.toString();
    }
}
