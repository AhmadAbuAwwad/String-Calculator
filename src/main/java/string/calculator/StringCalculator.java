package string.calculator;

import string.calculator.exception.ErrorMessages;
import string.calculator.exception.InvalidInputException;
import string.calculator.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    /**
     * Adds numbers provided in a string separated by commas or new lines.
     *
     * @param numbers A string of numbers separated by commas or new lines.
     * @return The sum of the numbers.
     */
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<Integer> numList = StringUtils.splitString(numbers);

        int sum = numList.stream().collect(Collectors.summingInt(Integer::intValue));
        return sum;
    }

    /**
     * Validates the input array to not exceed 2 numbers used in step 1.
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
