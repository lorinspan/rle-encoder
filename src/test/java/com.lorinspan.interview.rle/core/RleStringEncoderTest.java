package com.lorinspan.interview.rle.core;

import com.lorinspan.interview.rle.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RleStringEncoderTest {

    private final StringEncoder encoder = new RleStringEncoder();

    @ParameterizedTest(name = "Scenario {index}: input=''{0}'', expected=''{1}''")
    @CsvSource({
            "aaaabbbccc, a4b3c3",
            "abbbcdddd,  a1b3c1d4",
            "abc, a1b1c1",
            "z, z1",
            "aaaaa, a5",
            "aabbaa, a2b2a2"
    })
    @DisplayName("Should correctly encode various string patterns")
    void shouldEncodeCorrectly(String input, String expectedOutput) {
        String result = encoder.encodeString(input);
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Should return empty string for empty input")
    void shouldHandleEmptyString() {
        String result = encoder.encodeString("");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should propagate validation exception for null string input")
    void shouldThrowForNullStringInput() {
        assertThatThrownBy(() -> encoder.encodeString(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Input cannot be null.");
    }

    @Test
    @DisplayName("Should correctly encode a character array directly")
    void shouldEncodeCharArrayCorrectly() {
        char[] input = {'A', 'A', 'B', 'B', 'C', 'D', 'D', 'D'};
        String result = encoder.encodeCharArray(input);

        assertThat(result).isEqualTo("A2B2C1D3");
    }

    @Test
    @DisplayName("Should propagate validation exception for null char array")
    void shouldThrowForNullCharArray() {
        assertThatThrownBy(() -> encoder.encodeCharArray(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Input cannot be null.");
    }

    @Test
    @DisplayName("Should handle empty char array")
    void shouldHandleEmptyCharArray() {
        char[] input = new char[0];
        String result = encoder.encodeCharArray(input);

        assertThat(result).isEmpty();
    }
}
