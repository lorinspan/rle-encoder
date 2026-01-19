package com.lorinspan.interview.rle;

import com.lorinspan.interview.rle.core.RleStringEncoder;
import com.lorinspan.interview.rle.core.StringEncoder;
import com.lorinspan.interview.rle.exception.RleException;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StringEncoder encoder = new RleStringEncoder();

        System.out.println("=== RLE Encoder Demo ===\n");

        System.out.println("--- Standard Scenarios (String) ---");
        List<String> testInputs = Arrays.asList("aaaabbbccc", "abbbcdddd", "abc", "");
        for (String input : testInputs) {
            process(encoder, input);
        }

        System.out.println("\n--- Standard Scenario (Char Array) ---");
        process(encoder, new char[]{'A', 'A', 'B', 'B', 'C', 'D', 'D', 'D'});

        System.out.println("\n--- Edge & Security Scenarios ---");
        process(encoder, (String) null);
        process(encoder, "A".repeat(1_000_000).toCharArray());
        process(encoder, "A".repeat(1_000_001));
    }

    private static void process(StringEncoder encoder, String input) {
        String formattedInput = format(input);
        try {
            long start = System.nanoTime();

            String result = encoder.encodeString(input);

            long durationNs = System.nanoTime() - start;
            printResult(formattedInput, result, durationNs);

        } catch (RleException exception) {
            printError(formattedInput, exception);
        }
    }

    private static void process(StringEncoder encoder, char[] input) {
        String formattedInput = input == null ? "[NULL]" : format(new String(input));

        try {
            long start = System.nanoTime();

            String result = encoder.encodeCharArray(input);

            long durationNs = System.nanoTime() - start;
            printResult(formattedInput, result, durationNs);

        } catch (RleException exception) {
            printError(formattedInput, exception);
        }
    }

    private static void printResult(String input, String output, long durationNs) {
        String timeDisplay = (durationNs > 1_000_000)
                ? (durationNs / 1_000_000) + " ms"
                : durationNs + " ns";

        System.out.printf("Input: %-25s | Output: %-25s | Time: %s%n",
                input, format(output), timeDisplay);
    }

    private static void printError(String input, Exception exception) {
        System.err.printf("Input: %-25s | Failed: %s%n", input, exception.getMessage());
        try { Thread.sleep(10); } catch (InterruptedException ignored) {}
    }

    private static String format(String input) {
        if (input == null) return "[NULL]";
        return (input.length() > 20) ? "\"" + input.substring(0, 17) + "...\"" : "\"" + input + "\"";
    }
}