package com.lorinspan.interview.rle.validation;

import com.lorinspan.interview.rle.exception.InvalidInputException;

public class InputValidator {

    private static final int MAX_SAFE_LENGTH = 1_000_000;

    public void validate(String input) {
        if (input == null) {
            throw new InvalidInputException("Input string cannot be null.");
        }

        if (input.length() > MAX_SAFE_LENGTH) {
            throw new InvalidInputException(
                    String.format("Input length %d exceeds the maximum safe limit of %d.",
                            input.length(), MAX_SAFE_LENGTH)
            );
        }
    }
}
