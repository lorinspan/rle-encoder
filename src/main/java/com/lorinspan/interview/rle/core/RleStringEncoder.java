package com.lorinspan.interview.rle.core;

import com.lorinspan.interview.rle.exception.RleException;
import com.lorinspan.interview.rle.validation.InputValidator;

public class RleStringEncoder implements StringEncoder {

    private final InputValidator validator;

    public RleStringEncoder() {
        this.validator = new InputValidator();
    }

    public RleStringEncoder(InputValidator validator) {
        this.validator = validator;
    }

    @Override
    public String encode(String input) throws RleException {
        validator.validate(input);

        if (input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder(input.length());

        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); ++i) {
            char nextChar = input.charAt(i);

            if (nextChar == currentChar) {
                count++;
            } else {
                result.append(currentChar).append(count);

                currentChar = nextChar;
                count = 1;
            }
        }

        result.append(currentChar).append(count);

        return result.toString();
    }

    @Override
    public String encode(char[] input) throws RleException {
        if (input == null) {
            return encode((String) null);
        }
        return encode(new String(input));
    }
}
