package string.calculator;

import string.calculator.exception.ErrorMessages;
import string.calculator.exception.InvalidInputException;
import string.calculator.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    /**
     * Adds 0-2 numbers provided in a comma-separated string.
     *
     * @param numbers A string of numbers separated by commas.
     * @return The sum of the numbers.
     */
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<Character> delimiters = new ArrayList<>();
        delimiters.add(',');

        List<Integer> numList = StringUtils.splitString(numbers, delimiters);
        validateInput(numList);

        int sum = numList.stream().collect(Collectors.summingInt(Integer::intValue));
        return sum;
    }

    /**
     * Validates the input array to not exceed 2 numbers.
     *
     * @param numArray The array of numbers.
     * @throws InvalidInputException If the number of values exceeds 2.
     */
    private void validateInput(List<Integer> numArray) {
        if (numArray.size() > 2) {
            throw new InvalidInputException(ErrorMessages.INVALID_INPUT + "Number of inputs exceeds 2");
        }
    }
}
