package tdd_workshop.unit_testing.exercise.solution;

import static tdd_workshop.unit_testing.exercise.Factorial.factorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tdd_workshop.TestCase;

import java.util.stream.Stream;

public class FactorialTests {
    @Test
    public void testNegativeNumberThrowsException() {
        int input = -5;
        try {
            factorial(input);
            Assertions.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // this is expected
        }
    }

    @ParameterizedTest
    @MethodSource
    public void testFactorial(TestCase<Integer,Integer> testCase){
        int input = testCase.input;
        int expectedResult = testCase.expectedOutput;
        int actualResult = factorial(input);
        Assertions.assertEquals(expectedResult,actualResult);
    }
    private static Stream<TestCase<Integer,Integer>> testFactorial() {
        return Stream.of(
                new TestCase<>(0, 1),
                new TestCase<>(1, 1),
                new TestCase<>(2, 2),
                new TestCase<>(3, 6),
                new TestCase<>(4, 24),
                new TestCase<>(5, 120)
        );
    }
}
