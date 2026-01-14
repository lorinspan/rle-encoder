# Run-Length Encoding (RLE) Library

A Run-Length Encoding tool built to be fast and easy to read.

```
Exercise 1 - Run Length Encoding
Given a string, Your task is to  complete the function
encode that returns the run length encoded string for the given string.

eg if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6″.

You are required to complete the function encode that takes only one argument the string which is to be encoded and returns the encoded string.

Example 1:

Input:

str = aaaabbbccc

Output: a4b3c3

Explanation: a repeated 4 times

consecutively b 3 times, c also 3

times.

Example 2:

Input:

str = abbbcdddd

Output: a1b3c1d4

Your Task:

Complete the function encode() which takes a character array as a input parameter and returns the encoded string.You can use Java/C#/NodeJS for this exercise and an IDE of your choice.
```

## Features

* Uses a simple **O(n)** pass with `StringBuilder` to avoid unnecessary memory allocation;
* To keep things stable, it uses an `InputValidator` that caps length at 1,000,000 characters to prevent `OutOfMemoryError`;
* Logic is split into an interface-based encoder and a separate validation layer to keep the code organized;
* Includes full test coverage, including edge cases like empty strings and special characters.

## Project Structure

```
src/main/java/com/lorinspan/interview/rle
    -> core        # The main encoding logic and StringEncoder interface;
    -> exception   # Custom error handling (InvalidInputException);
    -> validation  # Input checks to prevent memory issues;
    -> Main.java   # Simple CLI to run the code.
```
## Prerequisites
* **Java 17** or higher;
* **Maven 3.6** or higher.

## Algorithm Analysis

* **Time complexity**: O(n):
  * Best case: O(n);
  * Worst case: O(n).
* **Space complexity**: O(n).

To avoid memory issues, the `InputValidator` rejects inputs larger than 1MB.

## Design Decision

The problem statement presented a minor contradiction:
```
[...] the function encode that takes only one argument the string [...];
[...] function encode() which takes a character array [...].
```
I included overloads for both `String` and `char[]` in the interface. Both share the same underlying logic to keep behaviour consistent and testing straightforward.

`Author: Lorin ȘPAN`