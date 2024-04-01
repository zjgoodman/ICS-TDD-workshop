package tdd_workshop;

import static tdd_workshop.Factorial.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class FactorialTests {
    @Test
    public void testNegativeNumberThrowsException() {
        int input = -5;
        try {
            factorial(input);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // this is expected
        }
    }

    @Test
    public void testGreaterThanOne() {
        int input = 5;
        int expectedResult = 120;
        int actualResult = factorial(input);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testZero() {
        assertEquals(1, factorial(0));
    }
}
