package tdd_workshop.tdd.exercise.solution;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {
    @Test
    public void testAdd() {
        runTest(Calculator::add, 2, 3, 5);
        runTest(Calculator::add, -1, -5, -6);
        runTest(Calculator::add, -5, 6, 1);
        runTest(Calculator::add, 0, 0, 0);
    }

    private void runTest(BiFunction<Integer, Integer, Integer> function, int input1, int input2, int expectedResult) {
        int actualResult = function.apply(input1, input2);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
