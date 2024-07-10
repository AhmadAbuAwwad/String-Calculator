package string.calculator.utils;

import string.calculator.exception.ErrorMessages;
import string.calculator.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    /**
     * Splits the input string by a list of delimiters.
     *
     * @param input      The string to be split.
     * @param delimiters A list of characters used to split the string.
     * @return An array of strings split by the delimiters.
     */
    public static List<Integer> splitString(String input, List<Character> delimiters) {
        List<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        DelimiterAndInput delimiterAndInput = separateDelimitersAndInput(input);
        input = delimiterAndInput.getInput();
        delimiters = delimiterAndInput.getDelimiters();

        boolean lastWasDelimiter = false;
        for (char ch : input.toCharArray()) {
            if (delimiters.contains(ch)) {
                if (lastWasDelimiter) {
                    throw new InvalidInputException(ErrorMessages.INVALID_INPUT + "Consecutive delimiters found");
                }
                lastWasDelimiter = true;

                addNumberStringToList(result, sb);
                sb.setLength(0);
            } else {
                lastWasDelimiter = false;
                sb.append(ch);
            }
        }
        addNumberStringToList(result, sb);
        validateNegatives(result);
        return result;
    }

    /**
     * Validates that the result list does not contain negative numbers.
     * If negative numbers are found, throws an InvalidInputException with a message
     * containing all negative numbers.
     *
     * @param result The list of integers to validate.
     * @throws InvalidInputException If any negative number is found in the result list.
     */
    private static void validateNegatives(List<Integer> result) {
        List<Integer> negativeNumbers = new ArrayList<>();
        for (int number : result) {
            if (number < 0)
                negativeNumbers.add(number);
        }
        if (!negativeNumbers.isEmpty()) {
            throw new InvalidInputException(ErrorMessages.INVALID_INPUT + "Negatives not allowed: " + negativeNumbers.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }
    }

    /**
     * Parsing the input string.
     *
     * @param str The string to validate.
     * @return The parsed integer.
     * @throws NumberFormatException if the string is not a valid integer.
     */
    private static int parseString(String str) throws NumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessages.INVALID_INPUT + "Char: " + str + " is not a number");
        }
    }

    /**
     * Separates delimiters and input string from the input based on the "\n" delimiter line.
     *
     * @param input The input string potentially containing delimiter specification.
     * @return An object containing the list of delimiters and the trimmed input string.
     * @throws InvalidInputException If the string format is invalid and not separated by new lines.
     */
    private static DelimiterAndInput separateDelimitersAndInput(String input) {
        List<Character> delimiters = new ArrayList<>();
        if (!input.startsWith("\\")) {
            delimiters.add(',');
            delimiters.add('\n');
            return new DelimiterAndInput(delimiters, input);
        }

        if (input.contains("\n")) {
            delimiters.addAll(input.substring(1, input.indexOf('\n')).chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList()));

            if (input.length() == input.indexOf('\n') + 1) {
                return new DelimiterAndInput(delimiters, "");
            }

            input = input.substring(input.indexOf('\n') + 1);
            return new DelimiterAndInput(delimiters, input);
        }
        return new DelimiterAndInput(delimiters, input);
    }

    /**
     * Adds the substring to the result list as a number if it's not empty and
     * the number is <= 1000.
     *
     * @param result The list to add the number to.
     * @param sb     The StringBuilder containing the substring representing a number.
     * @throws InvalidInputException if the substring is not a valid integer.
     */
    private static void addNumberStringToList(List<Integer> result, StringBuilder sb) throws InvalidInputException {
        if (!sb.isEmpty()) {
            int number = parseString(sb.toString());
            if (number <= 1000) {
                result.add(number);
            }
        }
    }
}
