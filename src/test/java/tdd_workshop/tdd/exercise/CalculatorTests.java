package tdd_workshop.tdd.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTests {
    //    TODO your tests
    @Test
    public void testAddition() {
        int input1 = 1;
        int input2 = 2;
        int expectedOutput = 3;
        int actualOutput = Calculator.add(input1, input2);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testAddition2() {
        int input1 = 2;
        int input2 = 2;
        int expectedOutput = 4;
        int actualOutput = Calculator.add(input1, input2);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testExpression() {
        String input = "1 + 1";
        int expectedOutput = 2;
        int actualOutput = Calculator.compute(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void nonBinaryExpressionIsUnsupported(){
        String input = "1 + 1 + 1";
        try {
            Calculator.compute(input);
            Assertions.fail("expected error");
        } catch (Exception e) {
            // expected behavior
        }
    }

    @Test
    public void testMain() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String[] args = { "1 + 1" };
        Calculator.main(args);
        Assertions.assertEquals("2", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testMainMultipleArgs() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String[] args = { "1", "+", "1" };
        Calculator.main(args);
        Assertions.assertEquals("2", outputStreamCaptor.toString().trim());
    }


}
