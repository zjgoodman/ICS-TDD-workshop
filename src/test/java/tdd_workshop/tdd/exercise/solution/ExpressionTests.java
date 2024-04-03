package tdd_workshop.tdd.exercise.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class ExpressionTests {
    @Test
    public void testParseExpression() {
        String inputString = "1+1";
        Expression expectedExpression = new Expression('+', 1, 1);
        Expression actualExpression = new Expression(inputString);
        Assertions.assertEquals(expectedExpression, actualExpression);
    }
    @Test
    public void testParseMultiDigitExpression() {
        String inputString = "123+456";
        Expression expectedExpression = new Expression('+', 123, 456);
        Expression actualExpression = new Expression(inputString);
        Assertions.assertEquals(expectedExpression, actualExpression);
    }
    @Test
    public void testMultiOperationExpressionGetsTruncated() {
        String inputString = "123+456+789";
        Expression expectedExpression = new Expression('+', 123, 456);
        Expression actualExpression = new Expression(inputString);
        Assertions.assertEquals(expectedExpression, actualExpression);
    }

    @Test
    public void testSplitString() {
        String inputString = "12345+67890+1+45";
        Set<Character> delimiters = Set.of('+');
        var expectedSplit = List.of("12345","+","67890","+","1","+","45");
        var actualSplit = Expression.splitString(inputString, delimiters);
        Assertions.assertEquals(expectedSplit, actualSplit);
    }

    @Test
    public void testEvaluate() {
        Expression expression = new Expression('+', 1, 2);
        int expectedResult = 3;
        int actualResult = expression.evaluate();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluateFromString() {
        runEvaluateTest("1+1", 2);
        runEvaluateTest("8+2", 10);
        runEvaluateTest("4-1", 3);
        runEvaluateTest("1-4", -3);
        runEvaluateTest("1*4", 4);
        runEvaluateTest("2*3", 6);
        runEvaluateTest("0*12", 0);
        runEvaluateTest("1/2", 0);
        runEvaluateTest("4/2", 2);
        runEvaluateTest("7/2", 3);
    }

    private void runEvaluateTest(String expression, int expectedResult) {
        Expression expressionObject = new Expression(expression);
        int actualResult = expressionObject.evaluate();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluateUnsupportedOperation() {
        try {
            runEvaluateTest("2^2", 0);
            Assertions.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // this is the expected behavior
        }
    }
}
