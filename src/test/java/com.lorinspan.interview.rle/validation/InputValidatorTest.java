package com.lorinspan.interview.rle.validation;

import com.lorinspan.interview.rle.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidatorTest {

    private final InputValidator validator = new InputValidator();

    @Test
    @DisplayName("Should pass for valid inputs")
    void shouldPassForValidInputs() {
        String validString = "A".repeat(100);

        assertThatCode(() -> validator.validate(validString)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Should throw exception when input is null")
    void shouldThrowWhenInputIsNull() {
        assertThatThrownBy(() -> validator.validate(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Input string cannot be null.");
    }

    @Test
    @DisplayName("Should throw exception when input exceeds max length")
    void shouldThrowWhenInputIsTooLarge() {
        String massiveString = "A".repeat(1_000_001);

        assertThatThrownBy(() -> validator.validate(massiveString))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("exceeds the maximum safe limit");
    }

    @Test
    @DisplayName("Should pass for empty string")
    void shouldPassForEmptyString() {
        assertThatCode(() -> validator.validate(""))
                .doesNotThrowAnyException();
    }
}
