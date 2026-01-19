package com.lorinspan.interview.rle.validation;

import com.lorinspan.interview.rle.exception.InvalidInputException;
import java.util.regex.Pattern;

public class InputValidator {

    private static final int MAX_SAFE_LENGTH = loadMaxSafeLength();
    private static final Pattern SAFE_PATTERN = Pattern.compile("^[a-zA-Z0-9]*$");

    public void validate(String input) {
        if (input == null) {
            throwNull();
        }

        if (input.length() > MAX_SAFE_LENGTH) {
            throwLength(input.length());
        }

        if (!SAFE_PATTERN.matcher(input).matches()) {
            throwInvalid();
        }
    }

    public void validate(char[] input) {
        if (input == null) {
            throwNull();
        }

        if (input.length > MAX_SAFE_LENGTH) {
            throwLength(input.length);
        }

        for (char c : input) {
            if (!isAlphanumeric(c)) {
                throwInvalid();
            }
        }
    }

    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    private static int loadMaxSafeLength() {
        try {
            String env = System.getenv("RLE_MAX_LENGTH");
            if (env != null && !env.isBlank()) {
                int parsed = Integer.parseInt(env);
                if (parsed > 0) {
                    return parsed;
                }
            }
        } catch (NumberFormatException ignored) {}
        return 1_000_000;
    }

    private void throwNull() {
        throw new InvalidInputException("Input cannot be null.");
    }

    private void throwLength(int length) {
        throw new InvalidInputException(
                String.format("Input length %d exceeds the maximum safe limit of %d.",
                        length, MAX_SAFE_LENGTH)
        );
    }

    private void throwInvalid() {
        throw new InvalidInputException("Input contains invalid characters. " +
                "Only alphanumeric characters are allowed to prevent injection attacks.");
    }
}