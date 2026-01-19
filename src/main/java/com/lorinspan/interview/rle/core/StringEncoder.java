package com.lorinspan.interview.rle.core;

import com.lorinspan.interview.rle.exception.RleException;

public interface StringEncoder {

    /**
     *
     * @param input the raw string to encode.
     * @return the encoded string
     * @throws RleException if the encoding fails or input is invalid.
     */
    String encodeString(String input) throws RleException;

    /**
     *
     * @param input the character array to encode.
     * @return the encoded string
     * @throws RleException if the encoding fails or input is invalid.
     */
    String encodeCharArray(char[] input) throws RleException;
}
