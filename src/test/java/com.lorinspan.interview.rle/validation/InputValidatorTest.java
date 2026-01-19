package com.lorinspan.interview.rle.validation;

import com.lorinspan.interview.rle.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidatorTest {

    private final InputValidator validator = new InputValidator();

    @Test
    @DisplayName("Should pass for valid alphanumeric inputs")
    void shouldPassForValidInputs() {
        String validString = "ValidInput123";
        assertThatCode(() -> validator.validate(validString)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "Scenario {index}: input=''{0}'', expected=''InvalidInputException''")
    @CsvSource(value = {
            "user\\nadmin",
            "<script>",
            "admin --",
            "user@email",
            "Hello World"
    })
    @DisplayName("Should reject inputs violating the Security Allowlist")
    void shouldRejectInvalidContent(String maliciousInput) {
        assertThatThrownBy(() -> validator.validate(maliciousInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("Input contains invalid characters");
    }

    @Test
    @DisplayName("Should throw exception when string input is null")
    void shouldThrowWhenStringInputIsNull() {
        assertThatThrownBy(() -> validator.validate((String) null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Input cannot be null.");
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

    @Test
    @DisplayName("Should pass for valid alphanumeric char array")
    void shouldPassForValidCharArray() {
        char[] validChars = "ValidInput123".toCharArray();
        assertThatCode(() -> validator.validate(validChars)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "Scenario {index}: input=''{0}'', expected=''InvalidInputException''")
    @CsvSource(value = {
            "user\\nadmin",
            "<script>",
            "admin --",
            "user@email",
            "Hello World"
    })
    @DisplayName("Should reject char arrays violating the Security Allowlist")
    void shouldRejectInvalidCharArrayContent(String maliciousInput) {
        char[] maliciousChars = maliciousInput.toCharArray();

        assertThatThrownBy(() -> validator.validate(maliciousChars))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("Input contains invalid characters");
    }

    @Test
    @DisplayName("Should throw exception when char array input is null")
    void shouldThrowWhenCharArrayInputIsNull() {
        assertThatThrownBy(() -> validator.validate((char[]) null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Input cannot be null.");
    }

    @Test
    @DisplayName("Should throw exception when char array exceeds max length")
    void shouldThrowWhenCharArrayIsTooLarge() {
        char[] massiveArray = new char[1_000_001];

        assertThatThrownBy(() -> validator.validate(massiveArray))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("exceeds the maximum safe limit");
    }

    @Test
    @DisplayName("Should pass for empty char array")
    void shouldPassForEmptyCharArray() {
        assertThatCode(() -> validator.validate(new char[0]))
                .doesNotThrowAnyException();
    }
}
