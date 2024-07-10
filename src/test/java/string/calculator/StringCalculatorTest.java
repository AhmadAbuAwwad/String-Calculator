package string.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import string.calculator.exception.ErrorMessages;
import string.calculator.exception.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void setUp() {
        stringCalculator = new StringCalculator();
    }


    /**
     * Tests the add method with an empty string input.
     * Expected result: Returns 0.
     */
    @Test
    public void testAdd_EmptyString_ReturnsZero() {
        assertEquals(0, stringCalculator.add(""));
    }

    /**
     * Tests the add method with a single number input.
     * Expected result: Returns the number itself.
     */
    @Test
    public void testAdd_SingleNumber_ReturnsNumber() {
        assertEquals(5, stringCalculator.add("5"));
    }

    /**
     * Tests the add method with two numbers separated by a comma.
     * Expected result: Returns the sum of the two numbers.
     */
    @Test
    public void testAdd_TwoNumbers_ReturnsSum() {
        assertEquals(300, stringCalculator.add("100,200"));
    }


//    /**
//     * Tests that adding more than two numbers throws an InvalidInputException used in step 1.
//     * Expected result: Throws InvalidInputException with the appropriate error message.
//     */
//    @Test
//    public void testAdd_MoreThanTwoNumbers_ThrowsInvalidInputException() {
//        Exception exception = assertThrows(InvalidInputException.class, () -> {
//            stringCalculator.add("1,2,3");
//        });
//        assertEquals(ErrorMessages.INVALID_INPUT + "Number of inputs exceeds 2", exception.getMessage());
//    }

    /**
     * Tests consecutive delimiters throws an InvalidInputException.
     * Expected result: Throws InvalidInputException with the appropriate error message.
     */
    @Test
    public void testAdd_ConsecutiveDelimiters_ThrowsInvalidInputException() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            stringCalculator.add("\\,\n1,2,,3");
        });
        assertEquals(ErrorMessages.INVALID_INPUT + "Consecutive delimiters found", exception.getMessage());
    }

    /**
     * Tests that adding a non-numeric character throws an InvalidInputException.
     * Expected result: Throws InvalidInputException with the appropriate error message.
     */
    @Test
    public void testAdd_NonNumericCharacter_ThrowsInvalidInputException() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            stringCalculator.add("\\,\n1,?");
        });
        assertEquals(ErrorMessages.INVALID_INPUT + "Char: ? is not a number", exception.getMessage());
    }

    /**
     * Tests the add method with many numbers separated by delimiters.
     * Expected result: Returns the sum of all numbers.
     */
    @Test
    public void testAdd_ManyNumbers_ReturnsSum() {
        assertEquals(3, stringCalculator.add("\\;\n1;2"));
    }

    /**
     * Tests the add method with a negative number separated by delimiters.
     * Expected result: Throws InvalidInputException with negative numbers.
     */
    @Test
    public void testAdd_ManyNumbersWithNegatives_ThrowsException() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            stringCalculator.add("\\&,\n1,2&3&-14,-5");
        });
        assertEquals(ErrorMessages.INVALID_INPUT + "Negatives not allowed: -14, -5", exception.getMessage());
    }
}
