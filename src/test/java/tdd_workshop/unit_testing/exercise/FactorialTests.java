package tdd_workshop.unit_testing.exercise;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import static tdd_workshop.unit_testing.exercise.Factorial.factorial;

public class FactorialTests {
    @Test
    public void test() {
        Assertions.assertEquals(true, true);
    }
    @Test
    public void testIllegalArguments() {
        int input = -1;
        try {
            Factorial.factorial(input);
            Assertions.fail("Expected an exception to be thrown");
        } catch (IllegalArgumentException e){
            // expected
        }
    }

    @Test
    public void testPositiveNumber() {
        int input =5 ;
        int result = Factorial.factorial(input);
        Assertions.assertEquals(120, result);

    }
}
