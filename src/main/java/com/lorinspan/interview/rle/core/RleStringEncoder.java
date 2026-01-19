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
    public String encodeString(String input) throws RleException {
        validator.validate(input);

        return encode(input.toCharArray());
    }

    @Override
    public String encodeCharArray(char[] input) throws RleException {
        validator.validate(input);

        return encode(input);
    }

    private String encode(char[] chars) {
        if (chars.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(chars.length);

        char currentChar = chars[0];
        int count = 1;

        for (int i = 1; i < chars.length; i++) {
            char nextChar = chars[i];

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
}
