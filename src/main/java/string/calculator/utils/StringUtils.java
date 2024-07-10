package string.calculator.utils;

import string.calculator.exception.ErrorMessages;
import string.calculator.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

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

        input = trimLeadingDelimiters(input, delimiters);

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

        return result;
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
     * Trims leading delimiter characters from the input string.
     *
     * @param input      The string to be trimmed.
     * @param delimiters A list of characters used as delimiters.
     * @return The trimmed string.
     */
    private static String trimLeadingDelimiters(String input, List<Character> delimiters) {
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (!delimiters.contains(charArray[i])) {
                return input.substring(i);
            }
        }
        return input;
    }

    /**
     * Adds the substring to the result list as a number if it's not empty.
     *
     * @param result The list to add the number to.
     * @param sb     The StringBuilder containing the substring representing a number.
     * @throws InvalidInputException if the substring is not a valid integer.
     */
    private static void addNumberStringToList(List<Integer> result, StringBuilder sb) throws InvalidInputException {
        if (!sb.isEmpty()) {
            result.add(parseString(sb.toString()));
        }
    }
}
