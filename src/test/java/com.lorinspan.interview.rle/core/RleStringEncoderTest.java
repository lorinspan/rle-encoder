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
            "1122$$@@, 1222$2@2",
            "aabbaa, a2b2a2"
    })
    @DisplayName("Should correctly encode various string patterns")
    void shouldEncodeCorrectly(String input, String expectedOutput) {
        String result = encoder.encode(input);
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Should return empty string for empty input")
    void shouldHandleEmptyString() {
        String result = encoder.encode("");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should propagate validation exception for null input")
    void shouldThrowForNullInput() {
        assertThatThrownBy(() -> encoder.encode((String) null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Input string cannot be null.");
    }
}
